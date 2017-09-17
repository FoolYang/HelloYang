package com.android.liyang.puzzles.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.android.liyang.puzzles.PuzzleApplication;

/**
 * Created by liyang on 17/9/15.
 *
 * 获取屏幕的宽高、像素密度、像素转换等
 */

public class ScreenUtil {

    /**
     * 获取屏幕相关参数
     *
     * @param context context
     * @return DisplayMetrics 屏幕宽高
     */
    public static DisplayMetrics getScreenSize(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics;
    }

    /**
     * 获取屏幕density
     *
     * @param context context
     * @return  屏幕density
     */
    public static float getDeviceDensity(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

    /**
     * px转dp 尺寸大小不变
     * @param px px
     * @return dp
     */
    public static int px2dp(float px) {
        Context context = PuzzleApplication.getContext();
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px / scale + 0.5f);
    }

    /**
     * dp转px 尺寸大小不变
     * @param dp dp
     * @return px
     */
    public static int dp2px(float dp) {
        Context context = PuzzleApplication.getContext();
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }
}
