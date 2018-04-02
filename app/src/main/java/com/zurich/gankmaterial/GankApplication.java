package com.zurich.gankmaterial;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

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

        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
