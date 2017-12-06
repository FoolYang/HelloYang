// IOnNewBookArrivedListener.aidl
package com.android.liyang.arttest.chapter_2.aidl;

// Declare any non-default types here with import statements

import com.android.liyang.arttest.chapter_2.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
