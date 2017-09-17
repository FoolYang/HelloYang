package com.android.liyang.puzzles.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.liyang.puzzles.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyang on 17/9/16.
 */

public class PictureGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bitmap> mPictureList = new ArrayList<>();

    public PictureGridViewAdapter(Context context, List<Bitmap> pictureList) {
        mContext = context;
        mPictureList = pictureList;
    }

    @Override
    public int getCount() {
        return mPictureList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPictureList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView pictureItem = null;
        int density = (int) ScreenUtil.getDeviceDensity(mContext);
        if (convertView == null) {
            pictureItem = new ImageView(mContext);
            pictureItem.setLayoutParams(new GridView.LayoutParams(80 * density, 100 * density));
            pictureItem.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            pictureItem = (ImageView) convertView;
        }

        pictureItem.setBackgroundColor(Color.BLACK);
        pictureItem.setImageBitmap(mPictureList.get(position));

        return pictureItem;
    }

}
