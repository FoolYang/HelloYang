package com.android.liyang.puzzles.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.liyang.puzzles.dao.ItemBean;
import com.android.liyang.puzzles.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyang on 17/9/17.
 */

public class PuzzleGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ItemBean> mItemList = new ArrayList<>();

    public PuzzleGridViewAdapter(Context context, List<ItemBean> itemList) {
        mContext = context;
        mItemList = itemList;
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            int density = (int) ScreenUtil.getDeviceDensity(mContext);
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ScreenUtil.dp2px(80), ScreenUtil.dp2px(100)));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(mItemList.get(position).getBitmap());

        return imageView;
    }
}
