package com.android.liyang.arttest.chapter_2.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.android.liyang.arttest.R;

/**
 * Created by liyang on 17/12/7.
 */

public class ProviderActivity extends Activity {

    private static final String TAG = "chapter_2_provider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_2_provider_activity);

        Uri bookUri = Uri.parse("content://com.android.liyang.arttest.chapter_2.provider/book");
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "c程序艺术探索");
        getContentResolver().insert(bookUri, values);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.bookId = bookCursor.getInt(0);
            book.bookName = bookCursor.getString(1);
            Log.i(TAG, "query book:" + book.toString());
        }
        bookCursor.close();

        Uri userUri = Uri.parse("content://com.android.liyang.arttest.chapter_2.provider/user");
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.userId = userCursor.getInt(0);
            user.userName = userCursor.getString(1);
            user.isMale = userCursor.getInt(2) == 1;
            Log.i(TAG, "query user:" + user.toString());
        }
        userCursor.close();
    }
}
