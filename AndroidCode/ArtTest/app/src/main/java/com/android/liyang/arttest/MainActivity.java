package com.android.liyang.arttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * android艺术探索 代码演练
 *
 * 源码地址
 * https://github.com/singwhatiwanna/android-art-res
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.main_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.main_list));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        onClickChapter2();
                        break;
                    case 1:
                        onClickChapter3();
                        break;
                    case 2:
                        onClickChapter4();
                        break;
                    case 3:
                        onClickChapter5();
                        break;
                    case 4:
                        onClickChapter6();
                        break;
                    case 5:
                        onClickChapter7();
                        break;
                    case 6:
                        onClickChapter8();
                        break;
                    case 7:
                        onClickChapter9();
                        break;
                    case 8:
                        onClickChapter10();
                        break;
                    case 9:
                        onClickChapter11();
                        break;
                    case 10:
                        onClickChapter12();
                        break;

                    default:
                        break;
                }
            }
        });
    }

    private void onClickChapter2() {
        Log.i("art_test" , " -- onClickChapter2");
        startActivity(new Intent(this, com.android.liyang.arttest.chapter_2.provider.ProviderActivity.class));
    }

    private void onClickChapter3() {
        Log.i("art_test" , " -- onClickChapter3");
    }

    private void onClickChapter4() {
        Log.i("art_test" , " -- onClickChapter4");
    }

    private void onClickChapter5() {
        Log.i("art_test" , " -- onClickChapter5");
    }

    private void onClickChapter6() {
        Log.i("art_test" , " -- onClickChapter6");
    }

    private void onClickChapter7() {
        Log.i("art_test" , " -- onClickChapter7");
    }

    private void onClickChapter8() {
        Log.i("art_test" , " -- onClickChapter8");
    }
    private void onClickChapter9() {
        Log.i("art_test" , " -- onClickChapter9");
    }

    private void onClickChapter10() {
        Log.i("art_test" , " -- onClickChapter10");
    }

    private void onClickChapter11() {
        Log.i("art_test" , " -- onClickChapter11");
    }

    private void onClickChapter12() {
        Log.i("art_test" , " -- onClickChapter12");
    }

}
