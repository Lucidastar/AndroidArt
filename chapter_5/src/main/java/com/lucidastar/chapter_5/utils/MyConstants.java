package com.lucidastar.chapter_5.utils;

import android.os.Environment;

/**
 * Created by qiuyouzone on 2018/3/29.
 */

public class MyConstants {
    public static final String CHAPTER_5_PATH = Environment
            .getExternalStorageDirectory().getPath()
            + "/lucidastar/chapter_5/";

    public static final String CACHE_FILE_PATH = CHAPTER_5_PATH + "usercache";

    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;

    public static final String REMOTE_ACTION = "com.lucidastar.chapter.action_REMOTE";
    public static final String EXTRA_REMOTE_VIEWS = "extra_remoteViews";
}
