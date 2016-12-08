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

public class AndroidRemoteDataSource implements GankDataSource {

    private static AndroidRemoteDataSource INSTANCE;

    public static AndroidRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AndroidRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<GankData>> getGankDatas() {
        return HttpMethods.getInstance().getGankRandomDataList(GankConstans.REQUEST_TYPE_ANDROID, GankConstans.REQUEST_NORMAL_SIZE);
    }

    @Override
    public Observable<GankData> getGankData(@NonNull String gankId) {
        return null;
    }

    @Override
    public void refreshDatas() {

    }
}
