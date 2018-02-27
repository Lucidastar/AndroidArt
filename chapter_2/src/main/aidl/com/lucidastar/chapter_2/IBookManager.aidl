// IBookManager.aidl
package com.lucidastar.chapter_2;

// Declare any non-default types here with import statements
import com.lucidastar.chapter_2.model.Book;
import com.lucidastar.chapter_2.IOnNewBookArrivedListener;
interface IBookManager {

    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
