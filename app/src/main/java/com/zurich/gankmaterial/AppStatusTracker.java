package com.zurich.gankmaterial;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.zurich.gankmaterial.util.GankLogUtils;

public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "AppStatusTracker";
    private int activeCount;
    private Application application;
    private static AppStatusTracker tracker;
    private boolean isForground;

    private boolean isScreenOff;
    private DaemonReceiver receiver;
    private int appStatus;

    private AppStatusTracker(Application application) {
        this.application = application;
        appStatus = GankConstans.STATUS_FORCE_KILLED;
        application.registerActivityLifecycleCallbacks(this);
    }

    public static void init(Application application) {
        tracker = new AppStatusTracker(application);
    }

    public static AppStatusTracker getInstance() {
        return tracker;
    }

    public void setAppStatus(int status) {
        this.appStatus = status;
        if (status == GankConstans.STATUS_ONLINE) {
            if (receiver == null) {
                IntentFilter filter = new IntentFilter();
                filter.addAction(Intent.ACTION_SCREEN_OFF);
                receiver = new DaemonReceiver();
                application.registerReceiver(receiver, filter);
            }
        } else if (receiver != null) {
            application.unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        GankLogUtils.e(TAG, activity.toString() + " onActivityStarted");
        activeCount++;
        isForground = true;
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        activeCount--;
        if (activeCount == 0) {
            isForground = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public boolean isScreenOff() {
        return isScreenOff;
    }

    public boolean isForground() {
        return isForground;
    }

    public void setScreenOff(boolean screenOff) {
        isScreenOff = screenOff;
    }

    public int getAppStatus() {
        return appStatus;
    }

    private class DaemonReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            GankLogUtils.d(TAG, "onReceive:" + action);
            if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                AppStatusTracker.getInstance().setScreenOff(true);
            }
        }
    }
}
