package com.android.liyang.arttest.chapter_12;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.GridView;

import com.android.liyang.arttest.R;

/**
 * Created by liyang on 17/12/30.
 */

public class ImageActivity extends Activity implements AbsListView.OnScrollListener{

    private GridView mGridView;
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_12_image_loader_activity);
        initView();
        initData();
    }

    private void initData() {
        mAdapter = new ImageAdapter(ImageActivity.this);
        mGridView.setAdapter(mAdapter);
    }

    private void initView() {
        mGridView = (GridView) findViewById(R.id.gridView1);
        mGridView.setOnScrollListener(this);

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO: 17/12/30  handl idle   滑动停止的时候才加载view
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // TODO: 17/12/30  ignore
    }
}
