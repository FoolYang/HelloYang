package com.android.liyang.puzzles.dao;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyang on 17/9/17.
 */

public class BitmapStore {

    private static Map<String, Bitmap> bitmapMap;

    public static void storeBitmap(String path, Bitmap bitmap) {
        if (bitmapMap == null) {
            bitmapMap = new HashMap<>();
        }
        bitmapMap.put(path, bitmap);
    }

    public static Bitmap getBitmap(String path) {
        if (bitmapMap == null) {
            return null;
        }
        Bitmap bitmap = bitmapMap.remove(path);
        if (bitmapMap.size() == 0) {
            bitmapMap = null;
        }
        return bitmap;
    }
}
