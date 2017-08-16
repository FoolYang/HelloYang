package com.example.liyang.helloandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyang on 17/8/16.
 */

public class MainAdapter extends BaseAdapter {

    private List<String> mDataList;
    private Context mContext;

    public MainAdapter (Context context, List<String> dataList) {
        this.mContext = context;
        if (dataList != null && !dataList.isEmpty()) {
            this.mDataList = dataList;
        } else {
            mDataList = new ArrayList<>();
        }

    }



    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_main_item_view, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.item_textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(mDataList.get(position));

        return convertView;
    }

    private class ViewHolder {
        TextView textView;
    }
}
