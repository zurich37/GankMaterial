package com.zurich.gankmaterial.data.source.remote;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;

import com.zurich.gankmaterial.GankApplication;
import com.zurich.gankmaterial.data.LocalAppInfo;
import com.zurich.gankmaterial.data.source.LocalAppInfoSource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 获取本地已安装列表
 * Created by weixinfei on 2016/12/8.
 */

public class LocalAppInfoRemoteDataSource implements LocalAppInfoSource {
    private static LocalAppInfoRemoteDataSource INSTANCE;
    private final static Map<Integer, LocalAppInfo> LOCAL_APP_INFO_MAP;

    static {
        LOCAL_APP_INFO_MAP = new LinkedHashMap<>();
    }

    public static LocalAppInfoRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalAppInfoRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<LocalAppInfo>> getAppInfos() {
        final PackageManager pm = GankApplication.getContext().getPackageManager();
        return Observable.create(new Observable.OnSubscribe<PackageInfo>() {
            @Override
            public void call(Subscriber<? super PackageInfo> subscriber) {
                List<PackageInfo> infoList = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
                for (PackageInfo applicationInfo : infoList) {
                    subscriber.onNext(applicationInfo);
                }
                subscriber.onCompleted();
            }
        }).filter(new Func1<PackageInfo, Boolean>() {
            @Override
            public Boolean call(PackageInfo applicationInfo) {
                return applicationInfo.applicationInfo.enabled;
            }
        }).map(new Func1<PackageInfo, LocalAppInfo>() {
            @Override
            public LocalAppInfo call(PackageInfo packageInfo) {
                LocalAppInfo appInfo = new LocalAppInfo();
                appInfo.id = packageInfo.applicationInfo.uid;
                appInfo.name = packageInfo.applicationInfo.loadLabel(pm).toString();
                appInfo.shortDesc = packageInfo.applicationInfo.loadDescription(pm) != null ? packageInfo.applicationInfo.loadDescription(pm).toString() : "";
                appInfo.packageName = packageInfo.packageName;
                appInfo.versionCode = packageInfo.versionCode;
                appInfo.versionName = packageInfo.versionName;
                return appInfo;
            }
        }).doOnNext(new Action1<LocalAppInfo>() {
            @Override
            public void call(LocalAppInfo appInfo) {
                LOCAL_APP_INFO_MAP.put(appInfo.id, appInfo);
            }
        }).toList();
    }

    @Override
    public Observable<LocalAppInfo> getAppInfo(@NonNull int appId) {
        final LocalAppInfo appInfo = LOCAL_APP_INFO_MAP.get(appId);
        if(appInfo != null) {
            return Observable.just(appInfo);
        } else {
            return Observable.empty();
        }
    }

    @Override
    public void refreshAppInfos() {

    }

    @Override
    public void deleteAppInfo(@NonNull int appId) {
        LOCAL_APP_INFO_MAP.remove(appId);
    }

    @Override
    public void saveAppInfo(LocalAppInfo appInfo) {
        LOCAL_APP_INFO_MAP.put(appInfo.id, appInfo);
    }
}
