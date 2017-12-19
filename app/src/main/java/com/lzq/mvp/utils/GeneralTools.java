package com.lzq.mvp.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by LZQ on 2017/12/6 17:01.
 * Describe :通用工具类
 */

public class GeneralTools {
    /**
     * 获取应用本地路径
     *
     * @return
     */
    public static File getAppDir() {
        File storyageDir = Environment.getExternalStorageDirectory();
        File cacheDir = new File(storyageDir, "UT");
        if (!cacheDir.exists()) {
            cacheDir.mkdir();
        }
        return cacheDir;
    }
}
