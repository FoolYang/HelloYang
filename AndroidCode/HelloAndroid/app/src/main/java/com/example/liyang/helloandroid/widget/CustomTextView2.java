package com.example.liyang.helloandroid.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by liyang on 17/8/7.
 *
 * 对现有组件扩张 实现一个文字闪动的效果
 */

public class CustomTextView2 extends TextView {

    private int mViewWidth;
    private Paint mPaint;
    private Matrix mGradientMatrix;
    private LinearGradient mLinearGradient;
    private int mTranslate;

    public CustomTextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomTextView2(Context context) {
        super(context);
    }

    public CustomTextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("shaa", "onSizeChanged..");
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            Log.i("shaa", "mViewWidth: " + mViewWidth);
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{Color.BLUE, 0xffffffff, Color.BLUE},
                        null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
//            Log.i("shaa", "mTranslate :" + mTranslate);
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }
}
