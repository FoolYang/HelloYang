package com.android.liyang.arttest.chapter_2.util;

import android.os.Environment;

/**
 * Created by liyang on 17/11/21.
 */

public class MyConstants {

    public static final String CHAPTER_2_PATH = Environment.getExternalStorageDirectory().getPath() + "/art/chapter_2/" ;

    public static final String CACHE_FILE_PATH = CHAPTER_2_PATH + "userCache";
}
