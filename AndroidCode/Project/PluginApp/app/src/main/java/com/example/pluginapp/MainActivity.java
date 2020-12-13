package com.example.pluginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.example.plugincore.PluginManager;
import com.example.plugincore.ProxyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpActivity(View view) {
        PluginManager.getInstance().setContext(getApplicationContext());
        // 加载插件
        PluginManager.getInstance().loadPlugin(Environment.getExternalStorageDirectory() + "/tm.apk");
        PackageInfo packageInfo = PluginManager.getInstance().getPackageInfo();
        Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
        if (packageInfo.activities.length <= 0) {
            return;
        }
        intent.putExtra("className", packageInfo.activities[0].name);
        startActivity(intent);
    }
}
