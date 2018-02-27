// IOnNewBookArrivedListener.aidl
package com.lucidastar.chapter_2;
import com.lucidastar.chapter_2.model.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
