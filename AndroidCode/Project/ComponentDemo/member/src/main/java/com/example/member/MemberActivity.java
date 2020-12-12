package com.example.member;

import android.os.Bundle;

import com.example.annotation.BindPath;
import com.example.basic.BaseActivity;

@BindPath("member/member")
public class MemberActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
    }
}
