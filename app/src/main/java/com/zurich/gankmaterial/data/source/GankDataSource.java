package com.zurich.gankmaterial.data.source;

import android.support.annotation.NonNull;

import com.zurich.gankmaterial.data.GankData;

import java.util.List;

import rx.Observable;

/**
 * Created by weixinfei on 2016/11/28.
 */

public interface GankDataSource {

    Observable<List<GankData>> getGankDatas();

    Observable<GankData> getGankData(@NonNull String gankId);

    void refreshDatas();
}
