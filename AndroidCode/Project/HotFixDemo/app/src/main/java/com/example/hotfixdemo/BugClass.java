package com.example.hotfixdemo;

import android.content.Context;
import android.widget.Toast;

public class BugClass {

    public BugClass(Context context) {
        Toast.makeText(context, "这是一个bug", Toast.LENGTH_LONG).show();
//        Toast.makeText(context, "bug 已修复..", Toast.LENGTH_LONG).show();
    }
}
