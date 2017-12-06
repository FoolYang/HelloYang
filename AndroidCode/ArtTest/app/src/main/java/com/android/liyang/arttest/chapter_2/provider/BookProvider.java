package com.android.liyang.arttest.chapter_2.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by liyang on 17/12/7.
 */

public class BookProvider extends ContentProvider {

    private static final String TAG = "chapter_2_provider";

    public static final String AUTHORITY = "com.android.liyang.arttest.chapter_2.provider";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    private SQLiteDatabase mDb;
    private Context mContext;

    @Override
    public boolean onCreate() {
        Log.i(TAG, "onCreate, current thread:" + Thread.currentThread().getName());
        mContext = getContext();
        initProviderData(); // 初始化数据库,只是演示,实际中不推荐主线程中执行耗时的数据库操作
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.i(TAG, "query, current thread:" + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (TextUtils.isEmpty(table)) {
            throw new IllegalArgumentException("unsupported uri:" + uri);
        }
        return mDb.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Override
    public String getType(Uri uri) {
        Log.i(TAG, "getType");
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i(TAG, "insert");
        String table = getTableName(uri);
        if (TextUtils.isEmpty(table)) {
            throw new IllegalArgumentException("unsupported uri:" + uri);
        }
        mDb.insert(table, null, values);
        mContext.getContentResolver().notifyChange(uri, null);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i(TAG, "delete");
        String table = getTableName(uri);
        if (TextUtils.isEmpty(table)) {
            throw new IllegalArgumentException("unsupported uri:" + uri);
        }
        int count = mDb.delete(table, selection, selectionArgs);
        if (count > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.i(TAG, "update");
        String table = getTableName(uri);
        if (TextUtils.isEmpty(table)) {
            throw new IllegalArgumentException("unsupported uri:" + uri);
        }
        int row = mDb.update(table, values, selection, selectionArgs);
        if (row > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return row;
    }

    private void initProviderData() {
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from " + DbOpenHelper.USER_TABLE_NAME);
        mDb.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);
        mDb.execSQL("insert into book values(3, 'android')");
        mDb.execSQL("insert into book values(4, 'ios')");
        mDb.execSQL("insert into book values(5, 'html')");
        mDb.execSQL("insert into user values(1, 'jake', 1)");
        mDb.execSQL("insert into user values(2, 'jasmine', 0)");
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch(sUriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
            default:
                break;
        }
        return tableName;
    }
}
