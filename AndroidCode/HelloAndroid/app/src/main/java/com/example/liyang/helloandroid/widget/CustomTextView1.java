package com.example.liyang.helloandroid.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by liyang on 17/8/20.
 *
 * 对现有组件进行扩展来实现自定义的view
 */

public class CustomTextView1 extends TextView {
    private Paint mPaint1;
    private Paint mPaint2;

    public CustomTextView1(Context context) {
        super(context);
        Log.i("liyang_shaa", "constructor 1 ..");
        init();
    }

    public CustomTextView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("liyang_shaa", "constructor 2 ..");
        init();
    }

    public CustomTextView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("liyang_shaa", "constructor 3 ..");
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        canvas.drawRect(10, 10, getMeasuredWidth() - 10  , getMeasuredHeight() - 10, mPaint2);
        canvas.save();
        canvas.translate(10, 0);
        super.onDraw(canvas);
        canvas.restore();
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);

    }
}
