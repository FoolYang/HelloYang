package com.example.liyang.helloandroid.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by liyang on 17/7/9.
 *
 * 重写view来实现全新的控件   弧线展示图
 */

public class CustomView extends View {
    private int mWidth;
    private int mHeight;

    private float mCircleXY;
    private float mRadius;

    private String mShowText;

    private RectF mArcRectF;

    private Paint mCirclePaint;
    private Paint mArcPaint;
    private Paint mTextPaint;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("liyang_custom_view", "CustomView 1 ..");
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("liyang_custom_view", "CustomView 2 ..");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.i("liyang_custom_view", "onMeasure ..");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("liyang_custom_view", "onDraw ..");

        init();

        // draw circle
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        // draw Arc
        canvas.drawArc(mArcRectF, 270, 200, false, mArcPaint);
        // draw text
        canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY - 50, mCircleXY + 10, mTextPaint);

    }

    private void init() {
        mWidth = getMeasuredWidth();
        mCircleXY = mWidth / 2;
        mRadius = (float)(mWidth * 0.5 / 2);
        mArcRectF = new RectF((float)(mWidth * 0.1), (float)(mWidth * 0.1), (float)(mWidth * 0.9), (float)(mWidth * 0.9));

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.YELLOW);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mArcPaint = new Paint();
        mArcPaint.setColor(Color.BLUE);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(50);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(30);

        mShowText = "弧线展示图";
    }
}
