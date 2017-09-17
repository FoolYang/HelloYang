package com.android.liyang.puzzles;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.android.liyang.puzzles.dao.BitmapStore;
import com.android.liyang.puzzles.ui.adapter.PictureGridViewAdapter;
import com.android.liyang.puzzles.ui.activity.PuzzleActivity;
import com.android.liyang.puzzles.util.Constants;
import com.android.liyang.puzzles.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PopupWindow mPopupWindow;
    private View mPopupView;
    private GridView mGridView;
    private List<Bitmap> mPictureList;
    private int[] mPictures;
    private int mType = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    private void initData() {
        mPictureList = new ArrayList<>();

        mPictures = new int[] {
                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
                R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
                R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
                R.drawable.pic10, R.drawable.pic11, R.drawable.pic12,
                R.drawable.pic13, R.drawable.pic14,
                R.drawable.pic15, R.mipmap.ic_launcher
        };

        Bitmap[] bitmaps = new Bitmap[mPictures.length];
        for (int i = 0; i < bitmaps.length; i++) {
            bitmaps[i]  = BitmapFactory.decodeResource(getResources(), mPictures[i]);
            mPictureList.add(bitmaps[i]);
        }
        mGridView.setAdapter(new PictureGridViewAdapter(this, mPictureList));
    }

    private void initView() {
        mPopupView = LayoutInflater.from(this).inflate(R.layout.layout_popup_window_view, null);
        mGridView = (GridView) findViewById(R.id.puzzle_picture_list);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 15) {
                    // showDialogCustom()
                } else {
                    BitmapStore.storeBitmap(Constants.KEY_BITMAP, mPictureList.get(position));

                    Intent intent = new Intent(MainActivity.this, PuzzleActivity.class);
                    intent.putExtra(Constants.SELECTED_ID, Constants.KEY_BITMAP);
                    intent.putExtra(Constants.TYPE, mType);
                    startActivity(intent);
                }
            }
        });
    }

    private void showPopupWindow(View view) {
        int density = (int) ScreenUtil.getDeviceDensity(this);
        mPopupWindow = new PopupWindow(mPopupView, 200 * density, 50 * density);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        Drawable transpant = new ColorDrawable(Color.TRANSPARENT);
        mPopupWindow.setBackgroundDrawable(transpant);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] - 40 * density, location[1] + 30 * density);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPictureList != null) {
            mPictureList = null;
        }
    }
}
