package com.android.liyang.arttest.chapter_12;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileDescriptor;

/**
 * Created by liyang on 17/12/28.
 *
 * 图片压缩功能的实现
 */

public class ImageResizer {

    private static final String TAG = "chapter_12";

    public ImageResizer() {

    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // first decode with inJustDecodeBounds = true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        //calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // decode bitmap width inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public Bitmap decodeSampleBitmapFromFileDescriptor(FileDescriptor fd, int reqWidth, int reqHeight) {
        // first decode width inJustDecodeBounds = true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);

        //calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        //decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd, null, options);

    }


    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        if (reqHeight == 0 || reqWidth == 0) {
            return 1;
        }

        // raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;

        Log.i(TAG, "origin, w=" + width + " h=" + height);
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        Log.i(TAG, "sampleSize:" + inSampleSize);
        return inSampleSize;
    }
}