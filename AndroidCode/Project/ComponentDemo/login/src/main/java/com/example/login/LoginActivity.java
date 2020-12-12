package com.example.login;

import android.os.Bundle;
import android.view.View;

import com.example.annotation.BindPath;
import com.example.basic.BaseActivity;
import com.example.router.Arouter;

@BindPath("login/login")
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void jumpActivity(View view) {
        Arouter.getInstance().jumpActivity("member/member", null);
    }
}
