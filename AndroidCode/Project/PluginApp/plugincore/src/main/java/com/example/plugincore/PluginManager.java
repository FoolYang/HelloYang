package com.example.plugincore;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 *
 * 加载插件的核心类
 *  1、获取到插件的资源对象
 *  2、获取到插件中的类加载器
 *  3、获取到插件的包信息类
 *
 */
public class PluginManager {

    private static PluginManager pluginManager = new PluginManager();
    // 上下文
    private Context mContext;
    // 插件的资源对象
    private Resources mPluginResource;
    // 插件的类加载器
    private DexClassLoader mDexClassLoader;
    // 插件的包信息类
    private PackageInfo mPackageInfo;

    public static PluginManager getInstance() {
        return pluginManager;
    }

    private PluginManager(){}


    public void setContext(Context context) {
        this.mContext = context;
    }

    public Resources getPluginResource() {
        return mPluginResource;
    }

    public DexClassLoader getDexClassLoader() {
        return mDexClassLoader;
    }

    public PackageInfo getPackageInfo() {
        return mPackageInfo;
    }


    /**
     * 去加载插件apk
     * @param pluginPath
     */
    public void loadPlugin(String pluginPath) {
        // 获取插件的包管理器
        PackageManager packageManager = mContext.getPackageManager();
        // 获取插件的包信息类
        mPackageInfo = packageManager.getPackageArchiveInfo(pluginPath, PackageManager.GET_ACTIVITIES);
        // 获取到插件解压后的目录
        File pluginFile = mContext.getDir("plugin", Context.MODE_PRIVATE);
        // 获取插件的类加载器
        mDexClassLoader = new DexClassLoader(pluginPath, pluginFile.getAbsolutePath(), null,
                mContext.getClassLoader());
        // 获取插件的资源对象
        try {
            // 创建一个AssetManager对象
            AssetManager assetManager = AssetManager.class.newInstance();
            // 拿到它的addAssetPath方法
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            // 执行方法
            addAssetPath.invoke(assetManager, pluginPath);
            mPluginResource = new Resources(assetManager, mContext.getResources().getDisplayMetrics(),
                    mContext.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
