package com.zurich.gankmaterial.data.source;

import androidx.annotation.NonNull;

import com.zurich.gankmaterial.data.LocalAppInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by weixinfei on 2016/12/8.
 */

public interface LocalAppInfoSource {

    Observable<List<LocalAppInfo>> getAppInfos();

    Observable<LocalAppInfo> getAppInfo(@NonNull int appId);

    void refreshAppInfos();

    void deleteAppInfo(@NonNull int appId);

    void saveAppInfo(LocalAppInfo appInfo);
}
