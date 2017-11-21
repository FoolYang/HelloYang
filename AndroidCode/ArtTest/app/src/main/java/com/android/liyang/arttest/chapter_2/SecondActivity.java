package com.android.liyang.arttest.chapter_2;

import android.app.Activity;
import android.os.Bundle;

import com.android.liyang.arttest.chapter_2.util.Chapter2Logger;
import com.android.liyang.arttest.chapter_2.util.MyConstants;
import com.android.liyang.arttest.chapter_2.util.MyUtils;
import com.android.liyang.arttest.R;
import com.android.liyang.arttest.chapter_2.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by liyang
 * on 17/11/21.
 */

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_2_activity_main_second);
        Chapter2Logger.log(" - first chapter 2 SecondActivity");
        recoverFromFile();
    }

    private void recoverFromFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = null;
                File cachedFile = new File(MyConstants.CACHE_FILE_PATH);
                if (cachedFile.exists()) {
                    Chapter2Logger.log("cachedFile ...");
                    ObjectInputStream objectInputStream = null;
                    try {
                        objectInputStream = new ObjectInputStream(new FileInputStream(cachedFile));
                        user = (User) objectInputStream.readObject();
                        Chapter2Logger.log("recover user:" + user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        MyUtils.close(objectInputStream);
                    }
                }
            }
        }).start();
    }
}
