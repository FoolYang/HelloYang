package com.example.componentdemo;


import com.example.basic.BaseApplication;
import com.example.router.Arouter;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Arouter.getInstance().init(this);
    }
}
