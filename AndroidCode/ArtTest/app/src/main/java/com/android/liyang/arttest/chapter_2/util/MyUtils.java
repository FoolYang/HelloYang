package com.android.liyang.arttest.chapter_2.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by liyang on 17/11/21.
 */

public class MyUtils {

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
