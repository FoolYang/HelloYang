package com.android.liyang.arttest.chapter_12;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.liyang.arttest.R;
import com.android.liyang.arttest.chapter_2.util.MyUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liyang on 17/12/28.
 * imageLoader
 *
 * 一个优秀的imageLoader应该具备如下功能:
 * 图片的同步加载
 * 图片的异步加载
 * 图片压缩
 * 内存缓存
 * 磁盘缓存
 * 网络拉取
 */

public class ImageLoader {

    private static final String TAG = "chapter_2_ImageLoader";

    public static final int MESSAGE_POST_RESULT = 1;

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final long KEEP_ALIVE = 10L;

    private static final int TAG_KEY_URI = 123;
    private static final long DISK_CACHE_SIZE = 1024 * 1024 * 50;
    private static final int IO_BUFFER_SIZE = 1024 * 8;
    private static final int DISK_CACHE_INDEX = 0;

    private boolean mIsDiskLruCacheCreated = false;


    private static final ThreadFactory sThreadFactory = new ThreadFactory() {

        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ImageLoader#" + mCount.getAndIncrement());
        }
    };

    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(),
            sThreadFactory);

    private Handler mMainHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            LoaderResult result = (LoaderResult)msg.obj;
            ImageView imageView = result.imageView;
            String uri = (String)imageView.getTag(TAG_KEY_URI);
            if (uri.equals(result.uri)) {
                imageView.setImageBitmap(result.bitmap);
            } else {
                Log.i(TAG, "set image bitmap, but url has changed, ignored!");
            }
        }
    };

    private Context mContext;
    private ImageResizer mImageReSizer = new ImageResizer();
    private LruCache<String, Bitmap> mMemoryCache;
//    private DiskLruCache mDiskLruCache;

    private ImageLoader(Context context) {
        mContext = context;
        int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight()/ 1024;
            }
        };

        File diskCacheDir = getDiskCacheDir(mContext, "bitmap");
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs();
        }

        if (getUsableSpace(diskCacheDir) > DISK_CACHE_SIZE) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * build a new instance of imageLoader
     * @param context
     * @return
     */
    public static ImageLoader build(Context context) {
        return new ImageLoader(context);
    }


    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public void bindBitmap(final String uri, final ImageView imageView) {
        bindBitmap(uri, imageView, 0, 0);
    }

    public void bindBitmap(final String uri, final ImageView imageView, final int reqWidth, final int reqHeight) {
        imageView.setTag(TAG_KEY_URI, uri);
        Bitmap bitmap = loadBitmapFromMemCache(uri);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadBitmap(uri, reqWidth, reqHeight);
                if (bitmap != null) {
                    LoaderResult result = new LoaderResult(imageView, uri, bitmap);
                    mMainHandler.obtainMessage(MESSAGE_POST_RESULT, result).sendToTarget();
                }
            }
        };

        THREAD_POOL_EXECUTOR.execute(runnable);
    }

    public Bitmap loadBitmap(String uri, int reqWidth, int reqHeight) {

        // step 1 , load from memCache
        Bitmap bitmap = loadBitmapFromMemCache(uri);
        if (bitmap != null) {
            Log.i(TAG, "loadBitmapFromMemCache, url:" + uri);
            return bitmap;
        }

        try {
            // step 2, load from diskCache
            //bitmap = loadBitmapFromDiskCache(uri, reqWidth, reqHeight);
//            if (bitmap != null) {
//                Log.i(TAG, "loadBitmapFromDiskCache, url:" + uri);
//                return bitmap;
//            }

            //step 3, load from http
            bitmap = loadBitmapFromHttp(uri, reqWidth, reqHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //step4, load from url
        if (bitmap == null && !mIsDiskLruCacheCreated) {
            Log.i(TAG, "encounter error, DiskLruCache is not created.");
            bitmap = downloadBitmapFromUrl(uri);
        }

        return bitmap;
    }


    private Bitmap loadBitmapFromMemCache(String url) {
        final String key = hashKeyFromUrl(url);
        Bitmap bitmap = getBitmapFromMemCache(key);
        return bitmap;
    }

    private Bitmap loadBitmapFromHttp(String url, int reqWidth, int reqHeight) throws IOException{
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("can not visit network from UI Thread");
        }

        if (!mIsDiskLruCacheCreated) {
            return null;
        }

        String key = hashKeyFromUrl(url);

        // TODO: 17/12/30  download url to stream, and diskLruCache flush, and load from disk cache
        return loadBitmapFromDiskCache(url, reqWidth, reqHeight);
    }

    private Bitmap loadBitmapFromDiskCache(String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.i(TAG, "load bitmao from ui thread, it is not recommended!");
        }

        if (!mIsDiskLruCacheCreated) {
            return null;
        }

        // TODO: 17/12/30  load from disk cache  (if bitmap not null, add to memCache)

        return null;
    }

    // TODO: 17/12/30  called in loadBitmapFromHttp(download,andload)
    public boolean downloadUrlToStream(String urlString, OutputStream os) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            out = new BufferedOutputStream(os, IO_BUFFER_SIZE);

            int b;
            while((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "download bitmap failed." + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                MyUtils.close(out);
                MyUtils.close(in);
            }
        }
        return false;
    }

    // DiskLruCache not create, load from url
    private Bitmap downloadBitmapFromUrl(String urlString) {
        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;

        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
        } catch (IOException e) {
            Log.i(TAG, "erroe in download bitmap :" + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                MyUtils.close(in);
            }
        }
        return bitmap;
    }

    private String hashKeyFromUrl(String url) {
        String cacheKey;
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            cacheKey =bytesToHashString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHashString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    private File getDiskCacheDir(Context context, String uniqueName) {
        boolean externalStorageAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if (externalStorageAvailable) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    private long getUsableSpace(File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return path.getUsableSpace();
        }
        final StatFs stats = new StatFs(path.getPath());
        return stats.getBlockSize() * (long)stats.getAvailableBlocks();
    }

    private static class LoaderResult {
        public ImageView imageView;
        public String uri;
        public Bitmap bitmap;

        public LoaderResult(ImageView imageView, String uri, Bitmap bitmap) {
            this.imageView = imageView;
            this.uri = uri;
            this.bitmap = bitmap;
        }
    }
}
