package com.zurich.gankmaterial.util;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zurich.gankmaterial.GankApplication;

/**
 * Created by weixinfei on 2017/10/9.
 */

public class CommonUtils {
    /**
     * 判断网络是否连接
     *
     * @return
     */
    public static boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) GankApplication.getContext()
                .getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
