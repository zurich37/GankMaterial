package com.zurich.gankmaterial.util;

import com.zurich.gankmaterial.GankApplication;
import com.zurich.gankmaterial.GankConstans;

/**
 * Created by weixinfei on 2017/10/9.
 */

public class FileUtils {
    /**
     * webview缓存目录
     *
     * @return
     */
    public static String getWebCacheDir() {
        return GankApplication.getContext().getFilesDir().getAbsolutePath() + GankConstans.WEB_CACHE_DIR;
    }
}
