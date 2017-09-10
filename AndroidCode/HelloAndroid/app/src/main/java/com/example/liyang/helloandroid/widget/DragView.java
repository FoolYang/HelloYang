package com.example.liyang.helloandroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by liyang on 17/9/9.
 *
 * android scroll分析:
 *
 * view提供的获取坐标方法:
 *  getTop: 获取到的是view自身的顶边到其父布局顶边的距离
 *  getLeft: 获取到的是view自身的左边到其父布局左边的距离
 *  getRight: 获取到的是view自身的右边到起父布局左边的距离
 *  getBottom: 获取到的是view自身的底边到其父布局顶边的距离
 *
 *  MotionEvent提供的方法:
 *  getX:获取到点击事件距离控件左边的距离,即视图坐标
 *  getY:获取到点击事件距离空间顶边的距离,即视图坐标
 *  getRawX:获取点击事件距离整个屏幕左边的距离,即绝对坐标
 *  getRawY:获取点击事件距离整个屏幕顶边的距离,即绝对坐标
 *
 *
 * dragView 用来实现滑动的组件
 *
 * - layout方法:滑动后调用onLayout方法来设置显示的位置
 *
 * - offsetLeftAndRight()与offsetTopAndBottom(): 相当于对左右上下移动的封装
 *
 * - LayoutParams: 动态的修改LayoutParams的位置参数 来改变view的位置
 *
 * - ScrollTo 与 ScrollBy
 *      ScrollTo: 从一个位置移动到另一个位置
 *      ScrollBy:移动增量
 *
 * - Scroller 可以实现平滑移动的效果
 *      本次效果是 组件滑动出去会自己平滑的移动回去
 */

public class DragView extends View {

    private int mLastX; // 滑动开始时的位置
    private int mLastY; // 滑动开始时的位置

    private Scroller mScroller;


    public DragView(Context context) {
        super(context);
        init();
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("scroll : ", "onLayout: left:"+left+" right:"+right);
    }

    private void init() {
        if (mScroller == null) {
            mScroller = new Scroller(getContext());
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 判断 mScroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View)getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 通过重绘来不断调用computeScroll
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = rawX;
                mLastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算便宜量
                int offSetX = rawX - mLastX;
                int offSetY = rawY - mLastY;
                // 在当前left top right bottom 的基础上加上偏移量

//                layout(getLeft() + offSetX,
//                        getTop() + offSetY,
//                        getRight() + offSetX,
//                        getBottom() + offSetY);


//                offsetLeftAndRight(offSetX);
//                offsetTopAndBottom(offSetY);

//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offSetX;
//                layoutParams.topMargin = getTop() + offSetY;
//                setLayoutParams(layoutParams);

//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offSetX;
//                layoutParams.topMargin = getTop() +offSetY;
//                setLayoutParams(layoutParams);

                ((View)getParent()).scrollBy(-offSetX, -offSetY);

                // 重新设置初始坐标
                mLastX = rawX;
                mLastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = (View)getParent();
                mScroller.startScroll(viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY(), 1500);
                invalidate();
                break;
        }

        return true;

    }
}
