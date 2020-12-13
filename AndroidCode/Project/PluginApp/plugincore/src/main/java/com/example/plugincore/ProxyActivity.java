package com.example.plugincore;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dalvik.system.DexClassLoader;

public class ProxyActivity extends AppCompatActivity {

    private PluginInterface pluginInterface;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        // 获取到真正目的地的类名
        String className = intent.getStringExtra("className");
        // 通过类加载器，去加载这个类
        DexClassLoader dexClassLoader = PluginManager.getInstance().getDexClassLoader();
        try {
            // 真正的目的地的Activity的类对象
            Class<?> aClass = dexClassLoader.loadClass(className);
            // 将它实例化
            Object pluginActivity = aClass.newInstance();
            // 判断pluginActivity 是否是PluginInterface接口的子类
            if (pluginActivity instanceof PluginInterface) {
                pluginInterface = (PluginInterface) pluginActivity;
                pluginInterface.attach(this);
                // 调用的是插件中的Activity的onCreate方法
                pluginInterface.onCreate(savedInstanceState);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("className", className);
        super.startActivity(intent1);
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getPluginResource();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pluginInterface.onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        pluginInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pluginInterface.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pluginInterface.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pluginInterface.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pluginInterface.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        pluginInterface.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pluginInterface.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        pluginInterface.onBackPressed();
        super.onBackPressed();
    }
}
