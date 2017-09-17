package com.android.liyang.puzzles.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.android.liyang.puzzles.R;
import com.android.liyang.puzzles.ui.PuzzleManager;
import com.android.liyang.puzzles.ui.adapter.PuzzleGridViewAdapter;
import com.android.liyang.puzzles.util.Constants;
import com.android.liyang.puzzles.util.GameUtil;
import com.android.liyang.puzzles.util.ImagesUtil;

/**
 * Created by liyang on 17/9/16.
 */

public class PuzzleActivity extends Activity {
    public static Bitmap mLastBitmap;
    private GridView mGridView;
    private PuzzleManager mPuzzleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_puzzle_main);
        initData();
        initView();

        int type = getIntent().getIntExtra(Constants.TYPE, 0);
        String bitmapKey = getIntent().getStringExtra(Constants.SELECTED_ID);

        mPuzzleManager = new PuzzleManager(this, mGridView);
        mPuzzleManager.onInit(bitmapKey, type);
    }

    private void initData() {
//        ImagesUtil imageUtil = new ImagesUtil();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mSelectedPictureId);
//        imageUtil.createInitBitmaps(TYPE, bitmap, this);
    }

    private void initView() {
        mGridView = (GridView) findViewById(R.id.puzzle_content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPuzzleManager.onDestroy();
    }
}
