// IBookManager.aidl
package com.android.liyang.arttest.chapter_2;

// Declare any non-default types here with import statements

import com.android.liyang.arttest.chapter_2.Book;

interface IBookManager {

    List<Book> getBooklist();
    void addBook(in Book book);

}
