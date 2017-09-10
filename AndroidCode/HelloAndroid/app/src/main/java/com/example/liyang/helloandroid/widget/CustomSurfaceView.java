package com.example.liyang.helloandroid.widget;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by liyang on 17/9/10.
 */

public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder; // SurfaceHolder
    private Canvas mCanvas; // 用于绘图的Canvas
    private boolean mIsDrawing; // 子线程标志位

    private Path mPath; // 绘制正弦曲线的path对象
    private Paint mPaint;
    private int x;
    private int y;

    public CustomSurfaceView(Context context) {
        super(context);
        init();
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
            x += 1;
            y = (int)(100 * Math.sin(x * 2 * Math.PI / 180) + 400);
            mPath.lineTo(x, y);
        }
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
//        mHolder.setFormat(PixelFormat.OPAQUE);

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLACK);
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            // draw some thing

            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
