package com.lucidastar.chapter_2.binderpool;

import android.os.RemoteException;

import com.lucidastar.chapter_2.binderpool.ICompute.Stub;

/**
 * Created by qiuyouzone on 2018/3/15.
 */

public class ComputeImpl extends Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
