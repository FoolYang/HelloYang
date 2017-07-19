package com.example.hdmddc2.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewListener();
    }

    private void initViewListener() {
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                VollyHelper.handleStringRequest(this);
                break;
            case R.id.btn_2:
                VollyHelper.handleJsonRequest(this);
                break;
            case R.id.btn_3:
                VollyHelper.handleImageRequest(this);
                break;
            case R.id.btn_4:
                VollyHelper.handleImageLoader(this);
                break;
            case R.id.btn_5:
                VollyHelper.handleNetWorkImageView(this);
                break;
            case R.id.btn_6:
                VollyHelper.handleXmlRequest(this);
                break;
            case R.id.btn_7:
                VollyHelper.handleGsonRequest(this);
                break;
            default:
                break;
        }
    }

}