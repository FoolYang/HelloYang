package com.android.liyang.puzzles.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.liyang.puzzles.R;
import com.android.liyang.puzzles.dao.BitmapStore;
import com.android.liyang.puzzles.dao.ItemBean;
import com.android.liyang.puzzles.ui.adapter.PuzzleGridViewAdapter;
import com.android.liyang.puzzles.util.Constants;
import com.android.liyang.puzzles.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyang on 17/9/17.
 */

public class PuzzleManager {

    private Context mContext;

    private GridView mGridView;

    private PuzzleGridViewAdapter mPuzzleAdapter;

    private List<ItemBean> mItemList = new ArrayList<>();

    private Bitmap mLastItemBitmap;

    private ItemBean mBlankItem;

    private PuzzleGridViewAdapter mAdapter;

    public PuzzleManager(Context context, GridView gridView) {
        mContext = context;
        mGridView = gridView;
    }

    public void onInit(String bitmapKey, int type) {
        Bitmap bitmap = BitmapStore.getBitmap(bitmapKey);

        if (bitmap != null) {
            Log.i(Constants.TAG, "bitmap width:" + bitmap.getWidth() + " height:" + bitmap.getHeight());
        }

        bitmap = resizeBitmap(400, 500, bitmap);

        if (bitmap != null) {
            Log.i(Constants.TAG, "bitmap width:" + bitmap.getWidth() + " height:" + bitmap.getHeight());
        }

        Log.i(Constants.TAG, "screen width:" + ScreenUtil.getScreenSize(mContext).widthPixels + " height:" + ScreenUtil.getScreenSize(mContext).heightPixels);

        initBitmaps(type, bitmap);
        mGridView.setNumColumns(type);
        mAdapter = new PuzzleGridViewAdapter(mContext, mItemList);
        mGridView.setAdapter(mAdapter);

        getPuzzleGenerator();

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isMoveable(position)) {
                    swapItems(mItemList.get(position), mBlankItem);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void onDestroy() {
        if (mItemList != null) {
            mItemList.clear();
            mItemList = null;
        }
    }

    // 切图 初始状态(正常顺序)
    private void initBitmaps(int type, Bitmap picSelected) {
        if (picSelected == null) {
            return;
        }
        Bitmap bitmap = null;
        List<Bitmap> bitmapItems = new ArrayList<>();
        int itemWidth = picSelected.getWidth() / type;
        int itemHeight = picSelected.getHeight() / type;
        for (int i = 1; i <= type; i++) {
            for (int j = 1; j <= type; j++) {
                bitmap = Bitmap.createBitmap(picSelected, (j - 1) * itemWidth, (i - 1) * itemHeight, itemWidth, itemHeight);
                bitmapItems.add(bitmap);
                ItemBean itemBean = new ItemBean((i - 1) * type + j, (i - 1) * type + j, bitmap);
                mItemList.add(itemBean);
//                GameUtil.mItemBeans.add(itemBean);
            }
        }
        // 保存最后一个图片在拼图完成时填充
        mLastItemBitmap = bitmapItems.get(type * type - 1);
        //设置最后一个为空item
        bitmapItems.remove(type * type -1);
        mItemList.remove(type * type -1);
//        GameUtil.mItemBeans.remove(type * type -1);
        Bitmap blankBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pic_blank);

        bitmapItems.add(blankBitmap);
        mItemList.add(new ItemBean(type * type, 0, blankBitmap));
//        GameUtil.mItemBeans.add(new ItemBean(type * type, 0, blankBitmap));
//        GameUtil.mBlankItemBean = GameUtil.mItemBeans.get(type * type - 1); // TODO: 17/9/17 ...
        mBlankItem = mItemList.get(type * type - 1);
    }

    // 处理图片 方法、缩小到合适位置
    private Bitmap resizeBitmap(float width, float height, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(width / bitmap.getWidth(), height / bitmap.getHeight());
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return newBitmap;
    }

    private int repeatCount = 0;
    // 生成随机的Item
    private void getPuzzleGenerator() {
        int index = 0;
        for (int i = 0; i < mItemList.size(); i++) {
            index = (int) (Math.random() * 4 * 4);
            swapItems(mItemList.get(index), mBlankItem);
        }

        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < mItemList.size(); i++) {
            data.add(mItemList.get(i).getBitmapId());
        }

        if (canSolve(data)) {
            return;
        } else if (repeatCount <= 1000){
            getPuzzleGenerator();
        }
        repeatCount++;

    }

    private void swapItems(ItemBean from, ItemBean blank) {
        ItemBean tempItemBean = new ItemBean();

        tempItemBean.setBitmapId(from.getBitmapId());
        from.setBitmapId(blank.getBitmapId());
        blank.setBitmapId(tempItemBean.getBitmapId());

        tempItemBean.setBitmap(from.getBitmap());
        from.setBitmap(blank.getBitmap());
        blank.setBitmap(tempItemBean.getBitmap());

        mBlankItem = from;

    }

    private  boolean canSolve(List<Integer> data) {
        int blankId = mBlankItem.getItemId();
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

    private  int getInversions(List<Integer> data) {
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

    public  boolean isMoveable(int position) {
        int type = 4;
        int blankId = mBlankItem.getItemId() - 1;
        if (Math.abs(blankId - position) == type) {
            return true;
        }
        if ((blankId /type == position / type) && Math.abs((blankId - position))== 1) {
            return true;
        }
        return false;
    }

    public  boolean isSuccess() {
        for (ItemBean tempBean : mItemList) {
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
