package com.zurich.gankmaterial;

import android.app.Application;
import android.content.Context;

/**
 *
 * Created by weixinfei on 2016/11/25.
 */
public class GankApplication extends Application {
    private static GankApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
