package com.zurich.gankmaterial.data.source.local;

import android.support.annotation.NonNull;

import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.data.source.GankDataSource;

import java.util.List;

import rx.Observable;

/**
 * Created by weixinfei on 2016/11/29.
 */
public class AndroidLocalDataSource implements GankDataSource {

    private static AndroidLocalDataSource INSTANCE;

    private List<GankData> datas;

    public static AndroidLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AndroidLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<GankData>> getGankDatas() {
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
