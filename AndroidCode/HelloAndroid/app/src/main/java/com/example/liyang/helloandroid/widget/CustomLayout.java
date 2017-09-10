package com.example.liyang.helloandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liyang.helloandroid.R;

/**
 * Created by liyang on 17/8/20.
 *
 * 通过重写一个view group 来创建一个复合组件 实现一个title bar的效果
 */

public class CustomLayout extends RelativeLayout {

    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;

    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;

    private TopBarClickListener mListener;

    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;

    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;

    private int mTitleTextColor;
    private float mTitleTextSize;
    private String mTitle;

    public CustomLayout(Context context) {
        super(context);
        Log.i("liyang_shaa", "init 1 .. ");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("liyang_shaa", "init 2 .. ");
        init(context, attrs);
        initView(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        initView(context);
        Log.i("liyang_shaa", "init 3 .. ");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("liyang_shaa", "onSizeChanged .. ");
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitle = ta.getString(R.styleable.TopBar_title);

        ta.recycle();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView(Context context) {
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftButton, mLeftParams);
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onLeftClick();
                }
            }
        });

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightClick();
                }
            }
        });

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);
    }

    public void setOnTopClickListener(TopBarClickListener listener) {
        mListener = listener;
    }

    public void setButtonVisable(int id, boolean flag) {

        if (id == 0) {
            mLeftButton.setVisibility(flag ? VISIBLE : GONE);
        } else {
            mRightButton.setVisibility(flag ? VISIBLE : GONE);
        }
    }

    public interface TopBarClickListener {
        void onLeftClick();
        void onRightClick();
    }
}
