package com.example.liyang.helloandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.liyang.helloandroid.activity.TestActivity1;
import com.example.liyang.helloandroid.activity.TestActivity2;
import com.example.liyang.helloandroid.activity.TestActivity3;
import com.example.liyang.helloandroid.activity.TestActivity4;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mDataList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClickListener();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        MainAdapter adapter = new MainAdapter(this.getBaseContext(), mDataList);
        mListView.setAdapter(adapter);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.list_view);
    }

    private void initData() {
        mDataList.add("测试页面 - 0");
        mDataList.add("测试页面 - 1");
        mDataList.add("测试页面 - 2");
        mDataList.add("测试页面 - 3");
        mDataList.add("测试页面 - 4");
        mDataList.add("测试页面 - 5");
        mDataList.add("测试页面 - 6");
        mDataList.add("测试页面 - 7");
        mDataList.add("测试页面 - 8");
        mDataList.add("测试页面 - 9");
        mDataList.add("测试页面 - 10");
        mDataList.add("测试页面 - 11");
        mDataList.add("测试页面 - 12");
        mDataList.add("测试页面 - 13");
        mDataList.add("测试页面 - 14");
        mDataList.add("测试页面 - 15");
    }

    private void initClickListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemViewClick(position);
            }
        });
    }

    private void onItemViewClick(int position) {
        Log.i("shaa", " listView position : " + position);
        switch (position) {
            case 0:
                this.startActivity(new Intent(this, TestActivity1.class));
                break;
            case 1:
                this.startActivity(new Intent(this, TestActivity2.class));
                break;
            case 2:
                this.startActivity(new Intent(this, TestActivity3.class));
                break;
            case 3:
                this.startActivity(new Intent(this, TestActivity4.class));
                break;
            default:
                break;
        }
    }
}