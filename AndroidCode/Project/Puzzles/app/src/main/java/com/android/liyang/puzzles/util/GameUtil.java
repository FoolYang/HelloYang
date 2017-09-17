package com.android.liyang.puzzles.util;

import com.android.liyang.puzzles.dao.ItemBean;
import com.android.liyang.puzzles.ui.activity.PuzzleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyang on 17/9/16.
 */

public class GameUtil {
    public static List<ItemBean> mItemBeans = new ArrayList<>();

    public static ItemBean mBlankItemBean;

    public static int repeatCount = 0;

    // 生成随机的Item
    public static void getPuzzleGenerator() {
        int index = 0;
        for (int i = 0; i < mItemBeans.size(); i++) {
            index = (int) (Math.random() * 4 * 4);
            swapItems(mItemBeans.get(index), GameUtil.mBlankItemBean);
        }

        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < mItemBeans.size(); i++) {
            data.add(mItemBeans.get(i).getBitmapId());
        }

        if (canSolve(data)) {
            return;
        } else if (repeatCount <= 1000){
            getPuzzleGenerator();
        }
        repeatCount++;

    }

    private static void swapItems(ItemBean from, ItemBean blank) {
        ItemBean tempItemBean = new ItemBean();

        tempItemBean.setBitmapId(from.getBitmapId());
        from.setBitmapId(blank.getBitmapId());
        blank.setBitmapId(tempItemBean.getBitmapId());

        tempItemBean.setBitmap(from.getBitmap());
        from.setBitmap(blank.getBitmap());
        blank.setBitmap(tempItemBean.getBitmap());

        mBlankItemBean = from;

    }

    private static boolean canSolve(List<Integer> data) {
        int blankId = GameUtil.mBlankItemBean.getItemId();
        if (data.size() % 2 == 1) {
            return getInversions(data) % 2 == 0;
        } else {
            if (((blankId - 1) / 4) % 2 == 1) {
                return getInversions(data) % 2 == 0;
            } else {
                return getInversions(data) % 2 == 1;
            }
        }
    }

    private static int getInversions(List<Integer> data) {
        int inversions = 0;
        int inversionCount = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                int index = data.get(i);
                if (data.get(j) != 0 && data.get(j) < index) {
                    inversionCount++;
                }
            }
            inversions += inversionCount;
            inversionCount = 0;
        }
        return inversions;
    }

    public static boolean isMoveable(int position) {
        int type = 4;
        int blankId = GameUtil.mBlankItemBean.getItemId() - 1;
        if (Math.abs(blankId - position) == type) {
            return true;
        }
        if ((blankId /type == position / type) && Math.abs((blankId - position))== 1) {
            return true;
        }
        return false;
    }

    public static boolean isSuccess() {
        for (ItemBean tempBean : GameUtil.mItemBeans) {
            if (tempBean.getBitmapId() != 0 && (tempBean.getItemId()) == tempBean.getBitmapId()) {
                continue;
            } else if (tempBean.getBitmapId() == 0 && tempBean.getItemId() == 4 * 4) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
