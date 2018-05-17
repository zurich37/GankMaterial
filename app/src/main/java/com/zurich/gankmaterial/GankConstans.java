package com.zurich.gankmaterial;

import android.os.Environment;

import java.io.File;

/**
 * Created by weixinfei on 2016/11/27.
 */

public interface GankConstans {

    public static final int STATUS_FORCE_KILLED = -1;
    public static final int STATUS_LOGOUT = 0;
    public static final int STATUS_OFFLINE = 1;
    public static final int STATUS_ONLINE = 2;
    public static final int STATUS_KICK_OUT = 3;

    public static final String KEY_HOME_ACTION = "key_home_action";
    public static final int ACTION_BACK_TO_HOME = 0;
    public static final int ACTION_RESTART_APP = 1;
    public static final int ACTION_LOGOUT = 2;
    public static final int ACTION_KICK_OUT = 3;

    int REQUEST_NORMAL_SIZE = 20;
    String REQUEST_TYPE_ANDROID = "Android";
    String REQUEST_TYPE_FULI = "福利";
    String REQUEST_TYPE_IOS = "iOS";
    String REQUEST_TYPE_REST_VIDEO = "休息视频";
    String REQUEST_TYPE_EXPAND_RES = "拓展资源";
    String REQUEST_TYPE_FOND_END = "前端";
    String WEB_CACHE_DIR = "WebCache";
    String WELFARE_DIR = GankApplication.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + "WelfarePic" + File.separator;
}
