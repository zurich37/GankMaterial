package com.zurich.gankmaterial.data;

/**
 * Created by weixinfei on 2016/12/8.
 */

public class LocalAppInfo {
    public int applicationId = -1;//服务端应用id
    public String packageName;//包名
    public String name;//应用名称
    public String icon;//应用图标
    public int versionCode;//版本号
    public String versionName;//版本名
    public String shortDesc;//短描述
    public String installDesc;//安装说明
    public String apkUrl;//apk下载链接
    public String md5;
    public int keyNum;//解锁次数信息
    public int tempDownloadState;//当前下载状态
    public long tempDownloadCurrentBytes;//当前已下载字节数
    public long size;//文件大小
    public String apkFilePath;//下载路径
    public int id;//download _ID
    public boolean isSystem;
    public boolean installed = false;
}
