package com.example.liyang.helloandroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by liyang on 17/8/30.
 *
 * 自定义 ViewGroup
 *
 * 自定义 ViewGroup 来实现一个类似android原生控件 ScrollView   并且带有粘性效果
 */

public class CustomViewGroup extends ViewGroup {

    private int mScreenHeight;
    private int mLastY;
    private int mStart;
    private int mEnd;
    private Scroller mScroller;


    public CustomViewGroup(Context context) {
        super(context);
        init();
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        WindowManager windowManager = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
        mScreenHeight = windowManager.getDefaultDisplay().getHeight();
        mScroller = new Scroller(this.getContext());
    }


    /**
     * 使用遍历的方式来通知子view对自身进行测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i ++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 通过遍历 设定每个子view需要放置的位置
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        // 设置ViewGroup的高度
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreenHeight * childCount;
        setLayoutParams(mlp);

        for (int i = 0; i < childCount; i ++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(l, i * mScreenHeight, r, (i + 1)*mScreenHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStart = getScrollY(); // 记录触摸起点
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                if (getScrollY() < 0) {
                    dy = 0;
                }
                if (getScrollY() > getHeight() - mScreenHeight) {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY(); // 记录触摸终点
                int dScrollY = mEnd - mStart;
                if (dScrollY > 0) {
                    if (dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, mScreenHeight - dScrollY);
                    }
                } else {
                    if (-dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight - dScrollY);
                    }
                }
                break;
        }
        postInvalidate();

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
