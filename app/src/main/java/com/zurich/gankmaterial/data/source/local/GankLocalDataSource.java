package com.zurich.gankmaterial.data.source.local;

import android.support.annotation.NonNull;

import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.data.source.GankDataSource;

import java.util.List;

import rx.Observable;

/**
 * Created by weixinfei on 2016/11/29.
 */
public class GankLocalDataSource implements GankDataSource {

    private static GankLocalDataSource INSTANCE;

    private List<GankData> datas;

    public static GankLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GankLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<GankData>> getGankDatas(String type, int page) {
        return null;
    }

    @Override
    public Observable<GankData> getGankData(@NonNull String gankId) {
        return null;
    }

    @Override
    public void refreshDatas() {

    }
}
