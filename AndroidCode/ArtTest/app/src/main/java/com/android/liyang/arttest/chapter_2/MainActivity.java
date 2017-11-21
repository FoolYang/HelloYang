package com.android.liyang.arttest.chapter_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.liyang.arttest.chapter_2.util.Chapter2Logger;
import com.android.liyang.arttest.chapter_2.util.MyConstants;
import com.android.liyang.arttest.chapter_2.util.MyUtils;
import com.android.liyang.arttest.R;
import com.android.liyang.arttest.chapter_2.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by liyang
 * on 17/11/21.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_2_activity_main);
        Chapter2Logger.log(" - first chapter 2 MainActivity");
        persistToFile();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        }, 2000);
    }

    private void persistToFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User(100, "shaa", false);
                File dir = new File(MyConstants.CHAPTER_2_PATH);
                if (!dir.exists()) {
                    boolean isSucceed = dir.mkdirs();
                    Chapter2Logger.log("isSucceed 1 :" + isSucceed);
                }
                File cachedFile = new File(MyConstants.CACHE_FILE_PATH);
                ObjectOutputStream objectOutputStream = null;
                try {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(cachedFile));
                    objectOutputStream.writeObject(user);
                    Chapter2Logger.log("persist book:" + user);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    MyUtils.close(objectOutputStream);
                }
            }
        }).start();
    }
}
