package com.example.mvvmdemo.model;

import android.util.Log;
import android.view.View;

public class TicViewModel {

    public String mText = "xxxxxx";


    public void onViewClick(View v) {
        Log.i("js_tag","onViewClick...");
    }

    public String getText() {
        return "111";
    }

}
