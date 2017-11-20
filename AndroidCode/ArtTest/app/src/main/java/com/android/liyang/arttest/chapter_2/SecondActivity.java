package com.android.liyang.arttest.chapter_2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.liyang.arttest.MyConstants;
import com.android.liyang.arttest.MyUtils;
import com.android.liyang.arttest.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by liyang
 * on 17/11/21.
 */

public class SecondActivity extends Activity {
    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recoverFromFile();
    }

    private void recoverFromFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Book book = null;
                File cachedFile = new File(MyConstants.CACHE_FILE_PATH);
                if (cachedFile.exists()) {
                    ObjectInputStream objectInputStream = null;
                    try {
                        objectInputStream = new ObjectInputStream(new FileInputStream(cachedFile));
                        book = (Book) objectInputStream.readObject();
                        Log.i(TAG, "recover book:" + book);
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
