package com.example.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.annotation.BindPath;

@BindPath("login2/login2")
public class LoginActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
