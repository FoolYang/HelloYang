package com.example.liyang.helloandroid.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liyang on 17/9/10.
 *
 * Android绘图技巧:
 *  绘制一个钟表
 */

public class DrawViewClock extends View {

    private float mWidth;
    private float mHeight;

    private Paint mPaintCircle;
    private Paint mPaintDegree;
    private Paint mPaintHour;
    private Paint mPaintMinute;

    public DrawViewClock(Context context) {
        super(context);
    }

    public DrawViewClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawViewClock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画外圆
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mPaintCircle);

        // 画刻度
        for(int i = 0; i < 24; i ++) {
            // 区分整点与非整点
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                mPaintDegree.setStrokeWidth(5);
                mPaintDegree.setTextSize(30);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,mWidth / 2, mHeight / 2 - mWidth /2 + 60, mPaintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - mPaintDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 90,
                        mPaintDegree);
            } else {
                mPaintDegree.setStrokeWidth(3);
                mPaintDegree.setTextSize(15);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,mWidth / 2, mHeight / 2 - mWidth /2 + 30, mPaintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - mPaintDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 60,
                        mPaintDegree);
            }
            // 通过旋转画布简化坐标运算
            canvas.rotate(15, mWidth / 2, mHeight / 2);
        }

        // 画指针
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 80, 120, mPaintHour);
        canvas.drawLine(0, 0, 80, 80, mPaintMinute);
        canvas.restore();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    private void init() {

        mPaintCircle = new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setAntiAlias(true);

        mPaintDegree = new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);

        mPaintHour = new Paint();
        mPaintHour.setStyle(Paint.Style.STROKE);
        mPaintHour.setStrokeWidth(15);

        mPaintMinute = new Paint();
        mPaintMinute.setStyle(Paint.Style.STROKE);
        mPaintMinute.setStrokeWidth(8);
    }

}
