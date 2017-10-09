package com.zurich.gankmaterial.data.source.remote;

import android.support.annotation.NonNull;

import com.zurich.gankmaterial.GankConstans;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.data.source.GankDataSource;
import com.zurich.gankmaterial.retrofit.HttpMethods;

import java.util.List;

import rx.Observable;

/**
 * Created by weixinfei on 2016/11/29.
 */

public class GankRemoteDataSource implements GankDataSource {

    private static GankRemoteDataSource INSTANCE;

    public static GankRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GankRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<GankData>> getGankDatas(String type, int page) {
        return HttpMethods.getInstance().getGankDatas(type, GankConstans.REQUEST_NORMAL_SIZE, page);
    }

    @Override
    public Observable<GankData> getGankData(@NonNull String gankId) {
        return null;
    }

    @Override
    public void refreshDatas() {

    }
}
