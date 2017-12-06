// IBookManager.aidl
package com.android.liyang.arttest.chapter_2.aidl;

// Declare any non-default types here with import statements

import com.android.liyang.arttest.chapter_2.aidl.Book;
import com.android.liyang.arttest.chapter_2.aidl.IOnNewBookArrivedListener;


interface IBookManager {

    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
