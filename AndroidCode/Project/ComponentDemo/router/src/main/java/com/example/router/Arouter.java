package com.example.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;


public class Arouter {

    private static Arouter mInstance = new Arouter();
    // 有一个容器，装载了所有的Activity类对象
    private Map<String, Class<? extends Activity>> map;
    private Context context;

    private Arouter() {
        map = new HashMap<>();
    }

    public static Arouter getInstance() {
        return mInstance;
    }

    public void init(Context context) {
        this.context = context;
        List<String> className = getClassName("com.example.util");
        for (String s : className) {
            Class<?> aClass = null;
            try {
                aClass = Class.forName(s);
                if (IRouter.class.isAssignableFrom(aClass)) {
                    // 通过接口的引用指向子类的实例
                    IRouter iRouter = (IRouter) aClass.newInstance();
                    iRouter.putActivity();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 跳转窗体的方法
     * @param key
     * @param bundle
     */
    public void jumpActivity(String key, Bundle bundle) {
        Class<? extends Activity> activityClass = map.get(key);
        if (activityClass != null) {
            Intent intent = new Intent(context, activityClass);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }


    /**
     * 通过包名获取这个包下面所有的类名
     * @param packageName
     */
    private List<String> getClassName(String packageName) {
        // 创建一个class对象的集合
        List<String> classList = new ArrayList<>();
        String path = null;
        try {
            // 通过包管理器，获取到应用信息类然后获取到APK的完整路径
            path = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            // 根据apk的完整路径获取到编译后的dex的文件目录
            DexFile dexFile = new DexFile(path);
            // 获取到编译后的dex文件中的所有class
            Enumeration entries = dexFile.entries();
            // 遍历当前应用的所有文件 com.example.util.ActivityUtil000
            while (entries.hasMoreElements()) {
                // 通过遍历所有的class的包名
                String name = (String) entries.nextElement();
                // 判断类的包名是否符合com.example.util
                if (name.contains(packageName)) {
                    // 如果符合，就添加到集合中
                    classList.add(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }


    /**
     * 将类对象 添加进map的方法
      * @param key
     * @param clazz
     */
    public void addActivity(String key, Class<? extends  Activity> clazz) {
        if (!TextUtils.isEmpty(key) && clazz != null && !map.containsKey(key)) {
            map.put(key, clazz);
        }
    }


}
