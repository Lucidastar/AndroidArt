// IBookManager.aidl
package com.lucidastar.chapter_2;

// Declare any non-default types here with import statements
import com.lucidastar.chapter_2.Book;

interface IBookManager {

List<Book> getBookList();
void addBook(in Book book);

}
