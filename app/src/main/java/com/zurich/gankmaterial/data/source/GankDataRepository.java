package com.zurich.gankmaterial.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.zurich.gankmaterial.data.GankData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 * Created by weixinfei on 2016/11/28.
 */
public class GankDataRepository implements GankDataSource {
    @Nullable
    private static GankDataRepository INSTANCE = null;

    @NonNull
    private final GankDataSource mRemoteDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    @Nullable
    private Map<String, GankData> mCachedGankDatas;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    private boolean mCacheIsDirty = false;

    private GankDataRepository(@NonNull GankDataSource dataSource) {
        this.mRemoteDataSource = dataSource;
    }

    public static GankDataRepository getInstance(@NonNull GankDataSource dataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GankDataRepository(dataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<GankData>> getGankDatas() {
        if (mCachedGankDatas != null && !mCacheIsDirty) {
            return Observable.from(mCachedGankDatas.values()).toList();
        } else if (mCachedGankDatas == null) {
            mCachedGankDatas = new LinkedHashMap<>();
        }
        Observable<List<GankData>> remoteData = getAndSaveRemoteDatas();
        if (mCacheIsDirty) {
            return remoteData;
        } else {
            Observable<List<GankData>> localCacheDatas = getAndCacheLocalDatas();
            return Observable.concat(localCacheDatas, remoteData)
                    .filter(new Func1<List<GankData>, Boolean>() {
                        @Override
                        public Boolean call(List<GankData> datas) {
                            return !datas.isEmpty();
                        }
                    }).first();
        }
    }

    /**
     * 获取本地缓存数据 todo:本地数据缓存没有实现
     *
     * @return
     */
    private Observable<List<GankData>> getAndCacheLocalDatas() {
        return mRemoteDataSource.getGankDatas()
                .flatMap(new Func1<List<GankData>, Observable<List<GankData>>>() {
                    @Override
                    public Observable<List<GankData>> call(List<GankData> gankData) {
                        return Observable.from(gankData)
                                .doOnNext(new Action1<GankData>() {
                                    @Override
                                    public void call(GankData gankData) {
                                        mCachedGankDatas.put(gankData._id, gankData);
                                    }
                                })
                                .toList();
                    }
                });
    }

    private Observable<List<GankData>> getAndSaveRemoteDatas() {
        return mRemoteDataSource
                .getGankDatas()
                .flatMap(new Func1<List<GankData>, Observable<List<GankData>>>() {
                    @Override
                    public Observable<List<GankData>> call(List<GankData> datas) {

                        return Observable.from(datas).doOnNext(new Action1<GankData>() {
                            @Override
                            public void call(GankData gankData) {
                                if (mCachedGankDatas != null) {
                                    mCachedGankDatas.put(gankData._id, gankData);
                                }
                            }
                        }).toList();
                    }
                }).doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        mCacheIsDirty = false;
                    }
                });
    }

    @Override
    public Observable<GankData> getGankData(@NonNull String gankId) {
        return null;
    }

    @Override
    public void refreshDatas() {
        mCacheIsDirty = true;
    }
}
