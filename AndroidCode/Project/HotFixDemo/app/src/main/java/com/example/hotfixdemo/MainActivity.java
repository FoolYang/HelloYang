package com.example.hotfixdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;

/**
 * 热修复实现原理：
 *
 * 所谓热修复，就是在我们应用上线后出现小bug需要及时修复时，不用再发新的安装包，只需发布补丁包，
 * 在客户不知不觉之间修复掉bug，Java虚拟机JVM在运行时，加载的是.classes的字节码文件。而Android也有自己的虚拟机
 * Dalvik/ART虚拟机，不过他们加载的是dex文件，但是他们的工作原理都一样，都是经过ClassLoader类加载器。Android在
 * ClassLoader的基础上又定义了PathClassLoader和DexClassLoader，两者都继承自BaseClassLoader，他们的区别如下：
 *      PathClassLoader:主要用来加载系统类和应用类
 *      DexClassLoader:用来加载jar、apk、dex文件，加载jar、apk也是最终抽取里面的Dex文件进行加载
 * Android热修复目前各大厂商都有自己的热修复工具，主要分为两大主流，以阿里为代表的Native层替换方式实现热
 * 修复【AndFIx、Sophix】等，和以腾讯美团为代表的在Java层实现热修复[Tinker，Robust等]。后者要实现热修复必须要重启
 * App，而前者则不需要重启App，直接在虚拟机的方法去实现方法替换。
 *
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        init();
    }


    /**
     * 启动页面时 调用检查热修复
     */

    private void init() {
        Log.i("js_tst", "hotfix, init.");
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        // 遍历所有的修复dex，因为可能是多个dex修复包
        File fileDir = externalStorageDirectory != null
                ? new File(externalStorageDirectory, "007")
                : new File(getFilesDir(), FixDexUtil.DEX_DIR); // data/data/0/包名/files/odex 这个可以任意位置
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        if (FixDexUtil.isGoingToFix(this)) {
            Log.i("js_tst", "hotfix, pre.");
            FixDexUtil.loadFixedDex(this, Environment.getExternalStorageDirectory());
            Log.i("js_tst", "hotfix, end.");
        }
    }

    public void clickButton(View view) {
//        FixDexUtil.isGoingToFix(this);
        init();

    }

    public void clickButton2(View view) {
        new BugClass(this);
    }
}
