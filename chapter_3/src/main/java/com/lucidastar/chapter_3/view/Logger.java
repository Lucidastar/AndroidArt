package com.lucidastar.chapter_3.view;

import android.util.Log;

/**
 * Created by Administrator on 2018/10/16.
 */

public class Logger {
    private final static boolean showTrace = false;
    private final static boolean onlyShowTopStack = false;

    // 过滤的方法堆栈
    private final static String[] FILTER_TRACE_PREFIX = {
            "java.lang",
            "android",
            "com.android",
            "com.lucidastar.Logger" // 过滤掉自己
    };

    public static void i(Object obj) {
        StringBuilder msg = new StringBuilder();
        if (showTrace) {
            StackTraceElement[] stes = Thread.currentThread().getStackTrace();
            for (int i = stes.length - 1, indent = 0; i > 0; i--) {
                boolean needFilter = false;
                String clsName = stes[i].getClassName();
                for (String filter : FILTER_TRACE_PREFIX) {
                    if (clsName.startsWith(filter)) {
                        needFilter = true;
                        break;
                    }
                }
                if (needFilter) continue;

                if (i != stes.length - 1)
                    msg.append(indent(indent)).append("-> ");
                msg.append(stes[i].getClassName() + "#" + stes[i].getMethodName() + "\n");
                indent++;
            }
        }
        msg.append(" >>> " + obj + "\n");
        String showMsg = msg.toString();
        if (onlyShowTopStack) {
            String[] trace = showMsg.split("->");
            showMsg = trace[trace.length - 1];
            showMsg = showMsg.replace("->", "")
                    .replace("\n", "")
                    .trim();
        }
        Log.i("Lucidastar", showMsg);
    }

    private static String indent(int i) {
        if (i < 0) {
            i = 0;
        }
        StringBuilder ret = new StringBuilder();
        for (int n = 0; n < i; n++) {
            ret.append(" ");
        }
        return ret.toString();
    }
}
