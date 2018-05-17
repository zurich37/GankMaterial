package com.zurich.gankmaterial.util;

import android.util.Log;

import com.zurich.gankmaterial.BuildConfig;

public class GankLogUtils {

    public static boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String tag, String msg) {
        if (!DEBUG) {
            return;
        }
        Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (!DEBUG) {
            return;
        }
        Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (!DEBUG) {
            return;
        }
        Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (!DEBUG) {
            return;
        }
        Log.v(tag, msg);
    }
}
