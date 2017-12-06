package com.android.liyang.arttest.chapter_2.provider;

/**
 * Created by liyang on 17/12/7.
 */

public class Book {
    public int bookId;
    public String bookName;

    @Override
    public String toString() {
        return "bookId:" + bookId + " , bookName:" + bookName;
    }
}
