package com.android.liyang.puzzles.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.android.liyang.puzzles.R;
import com.android.liyang.puzzles.dao.ItemBean;
import com.android.liyang.puzzles.ui.activity.PuzzleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyang on 17/9/16.
 */

public class ImagesUtil {
    public ItemBean itemBean;

    // 切图 初始状态(正常顺序)
    public void createInitBitmaps(int type, Bitmap picSelected, Context context) {
        Bitmap bitmap = null;
        List<Bitmap> bitmapItems = new ArrayList<>();
        int itemWidth = picSelected.getWidth() / type;
        int itemHeight = picSelected.getHeight() / type;
        for (int i = 1; i <= type; i++) {
            for (int j = 1; j <= type; j++) {
                bitmap = Bitmap.createBitmap(picSelected, (j - 1) * itemWidth, (j - 1) * itemHeight, itemWidth, itemHeight);
                bitmapItems.add(bitmap);
                itemBean = new ItemBean((i - 1) * type + j, (i - 1) * type + j, bitmap);
                GameUtil.mItemBeans.add(itemBean);
            }
        }
        // 保存最后一个图片在拼图完成时填充
        PuzzleActivity.mLastBitmap = bitmapItems.get(type * type - 1);
        //设置最后一个为空item
        bitmapItems.remove(type * type -1);
        GameUtil.mItemBeans.remove(type * type -1);
        Bitmap blankBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pic_blank);

        bitmapItems.add(blankBitmap);
        GameUtil.mItemBeans.add(new ItemBean(type * type, 0, blankBitmap));
        GameUtil.mBlankItemBean = GameUtil.mItemBeans.get(type * type - 1);
    }

    // 处理图片 方法、缩小到合适位置
    public Bitmap resizeBtmap(float width, float height, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(width / bitmap.getWidth(), height / bitmap.getHeight());
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return newBitmap;
    }
}
