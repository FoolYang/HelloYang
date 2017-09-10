package com.example.liyang.helloandroid.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by liyang on 17/8/24.
 *
 * 重写view来实现全新的控件  音频条形图
 *
 *
 */

public class CustomView2 extends View {

    private int mRectCount;
    private int mWidth;
    private int mRectWigth;
    private int mRectHeight;
    private int offSet;
    private float currentHeight;

    private Paint mPaint;

    public CustomView2(Context context) {
        super(context);
    }

    public CustomView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("liyang_customview2","-- onDraw");
        super.onDraw(canvas);
        init();

        for (int i = 0; i < mRectCount; i ++) {

            updateCurrentHeight();

            canvas.drawRect(
                    (float)(mWidth * 0.4 / 2 + mRectWigth * i + offSet),
                    currentHeight,
                    (float)(mWidth * 0.4 / 2 + mRectWigth * (i + 1)),
                    mRectHeight,
                    mPaint
            );
        }
        postInvalidateDelayed(200);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i("liyang_customview2","-- onSizeChanged");
        super.onSizeChanged(w, h, oldw, oldh);
        init();
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWigth = (int)(mWidth * 0.6 / mRectCount);
        LinearGradient linearGradient = new LinearGradient(
                0,
                0,
                mRectWigth,
                mRectHeight,
                Color.YELLOW,
                Color.BLUE,
                Shader.TileMode.CLAMP
        );
        mPaint.setShader(linearGradient);
    }

    private void updateCurrentHeight() {
        double random = Math.random();
        currentHeight = (float)(mRectHeight * random);
    }

    private void init() {
        mRectCount = 10;
        mWidth = getMeasuredWidth();
        mRectWigth = 40;
        mRectHeight = getMeasuredHeight();
        offSet = 5;

        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setColor(Color.BLACK);
            mPaint.setStyle(Paint.Style.FILL);
        }
    }
}
