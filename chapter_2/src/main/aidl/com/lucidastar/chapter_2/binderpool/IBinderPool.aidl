// IBinderPool.aidl
package com.lucidastar.chapter_2.binderpool;

// Declare any non-default types here with import statements

interface IBinderPool {
  IBinder queryBinder(int binderCode);
}
