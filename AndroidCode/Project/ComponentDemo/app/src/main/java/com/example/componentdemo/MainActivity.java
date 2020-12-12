package com.example.componentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.annotation.BindPath;
import com.example.router.Arouter;

@BindPath("main/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpActivity(View view) {
//        Boolean is_application = BuildConfig.is_application;
        Arouter.getInstance().jumpActivity("login/login", null);
    }
}
