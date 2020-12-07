package com.example.rxjavademo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Activity {

    private ProgressDialog progressDialog;
    private ImageView imgView;
    private final static String PATH = "http://pic1.win4000.com/wallpaper/c/53cdd1f7c1f21.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView = findViewById(R.id.js_img);
    }

    // rxJava下载图片 响应流事件  ：  起点（图片地址） -----> 终点（显示图片）
    // 上游 做耗时操作 下游做ui操作；订阅的前面做上游，订阅的后面是下游
    public void rxJavaDownloadImageAction(View view) {
        // 起点
        // 第二步，分发事件
        Observable.just(PATH)
                .map(new Function<String, Bitmap>() { // 第三步 执行 做变化
            @Override
            public Bitmap apply(String s) throws Exception {
                // 耗时操作
                URL url = new URL(PATH);
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                httpURLConnection.setConnectTimeout(5000);
                int responseCode = httpURLConnection.getResponseCode();
                if (HttpURLConnection.HTTP_OK == responseCode) {
                    return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                }
                return null;
            }
        })
                .map(new Function<Bitmap, Bitmap>() { // 中间加需求，针对bitmap做操作 给图片加水印

                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Exception {

                        Paint paint = new Paint();
                        paint.setColor(Color.RED);
                        paint.setTextSize(88);

                        Bitmap.Config bitmapConfig = bitmap.getConfig();
                        paint.setDither(true);
                        paint.setFilterBitmap(true);
                        if (bitmapConfig != null) {
                            bitmapConfig = Bitmap.Config.ARGB_8888;
                        }
                        bitmap = bitmap.copy(bitmapConfig, true);
                        Canvas canvas = new Canvas(bitmap);
                        canvas.drawText("shaa", 88, 88, paint);
                        return bitmap;
                    }
                })

                .subscribeOn(Schedulers.io()) // 给上游分配异步线程

                .observeOn(AndroidSchedulers.mainThread()) // 给下游分配android主线程

                // 订阅，把起点和终点 关联起来
                .subscribe(
                        // 终点
                        new Observer<Bitmap>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                // 第一步，最先执行，一订阅，马上执行

                                // 这里弹出对话框
                                progressDialog = new ProgressDialog(MainActivity.this);
                                progressDialog.setTitle("rx java run..");
                                progressDialog.show();

                            }

                            @Override
                            public void onNext(Bitmap bitmap) { // 从上游分发下来的事项，这里参数与最后一个map返回值一致
                                // 第四步
                                imgView.setImageBitmap(bitmap);
                            }

                            @Override
                            public void onError(Throwable e) {
                                // 执行onError，不会再执行onComplete
                            }

                            @Override
                            public void onComplete() {
                                // 第五步 最终点，整个链路全部结束
                                if (progressDialog != null && progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                            }
                        }
                );
    }


    // 传统方式 思维
    //
    public void downloadImageAction(View view) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("download run ... ");
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(PATH);
                    URLConnection urlConnection = url.openConnection();
                    HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                    httpURLConnection.setConnectTimeout(5000);
                    int responseCode = httpURLConnection.getResponseCode();
                    if (HttpURLConnection.HTTP_OK == responseCode) {
                        Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                        Message message = handler.obtainMessage();
                        message.obj = bitmap;
                        handler.sendMessage(message);
                    }
                } catch (Exception r) {

                }
            }
        }).start();
    }

    private final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Bitmap bitmap = (Bitmap) msg.obj;
            imgView.setImageBitmap(bitmap);
            return false;
        }
    });
}
