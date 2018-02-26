package com.lucidastar.chapter_2.manualbinder;

import android.os.IBinder;
import android.os.IInterface;

import com.lucidastar.chapter_2.model.Book;

import java.util.List;


/**
 * Created by qiuyouzone on 2018/2/13.
 */

public interface IBookManager extends IInterface{
     String DESCRIPTOR = "com.lucidastar.chapter_2.manualbinder.IBookManager";
     int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;
     int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    List<Book> getBookList() throws android.os.RemoteException;

     void addBook(Book book) throws android.os.RemoteException;
}
