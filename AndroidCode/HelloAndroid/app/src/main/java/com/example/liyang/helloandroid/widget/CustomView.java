package com.example.liyang.helloandroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by liyang on 17/7/9.
 */

public class CustomView extends TextView {

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specModeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int specModeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int specSizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specSizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        Log.i("liyang", " specModeWidth :" + specModeWidth+"\n");
        Log.i("liyang", " specModeHeight :" + specModeHeight+"\n");
        Log.i("liyang", " specSizeWidth :" + specSizeWidth+"\n");
        Log.i("liyang", " specSizeHeight :" + specSizeHeight+"\n");
        Log.i("liyang","------------------------------------------");

    }
}
