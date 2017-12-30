package com.android.liyang.arttest.chapter_12;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.liyang.arttest.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liyang on 17/12/30.
 */

public class ImageAdapter extends BaseAdapter {

    private static final List<String> mUrList = Arrays.asList("","");
    private Context mContext;
    private ImageLoader mImageLoader;

    public ImageAdapter(Context context) {
        mContext = context;
        mImageLoader = ImageLoader.build(mContext);
    }

    @Override
    public int getCount() {
        return mUrList.size();
    }

    @Override
    public String getItem(int position) {
        return mUrList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.chapter_12_image_grid_item, parent);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageView imageView = viewHolder.imageView;

        final String tag = (String)imageView.getTag();
        final String uri = getItem(position);

        if (!uri.equals(tag)) {
            imageView.setImageDrawable(null); // // TODO: 17/12/30  set defaultBitmapDrawable
        }

        imageView.setTag(uri);
        mImageLoader.bindBitmap(uri, imageView);

        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
    }
}
