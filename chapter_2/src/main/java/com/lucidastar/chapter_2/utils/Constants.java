package com.lucidastar.chapter_2.utils;

import android.os.Environment;

/**
 * Created by qiuyouzone on 2018/2/26.
 */

public class Constants {
    public static final String CHAPTER_2_PATH = Environment
            .getExternalStorageDirectory().getPath()
            + "/lucidastar/chapter_2/";

    public static final String CACHE_FILE_PATH = CHAPTER_2_PATH + "usercache";

    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;
}
