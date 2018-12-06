package com.zurich.gankmaterial.data.source;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import com.zurich.gankmaterial.data.LocalAppInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

import static rx.Observable.from;

/**
 * Created by weixinfei on 2016/12/8.
 */

public class LocalAppInfoRepository implements LocalAppInfoSource {
    @Nullable
    private static LocalAppInfoRepository INSTANCE = null;

    @NonNull
    private final LocalAppInfoSource mRemoteDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    @Nullable
    private Map<Integer, LocalAppInfo> mCachedLocalCachedDatas;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    private boolean mCacheIsDirty = false;

    public LocalAppInfoRepository(@NonNull LocalAppInfoSource mRemoteDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;
    }

    public static LocalAppInfoRepository getInstance(@NonNull LocalAppInfoSource dataSource) {
        if (INSTANCE == null) {
            INSTANCE = new LocalAppInfoRepository(dataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<LocalAppInfo>> getAppInfos() {
        if (mCachedLocalCachedDatas != null && !mCacheIsDirty) {
            return from(mCachedLocalCachedDatas.values()).toList();
        } else if (mCachedLocalCachedDatas == null) {
            mCachedLocalCachedDatas = new LinkedHashMap<>();
        }

        if (mCacheIsDirty) {
            return getAndCacheRemoteData();
        } else {
            return Observable.from(mCachedLocalCachedDatas.values()).toList();
        }
    }

    private Observable<List<LocalAppInfo>> getAndCacheRemoteData() {
        return mRemoteDataSource
                .getAppInfos()
                .flatMap(new Func1<List<LocalAppInfo>, Observable<List<LocalAppInfo>>>() {
                    @Override
                    public Observable<List<LocalAppInfo>> call(List<LocalAppInfo> localAppInfos) {
                        return from(localAppInfos)
                                .doOnNext(new Action1<LocalAppInfo>() {
                                    @Override
                                    public void call(LocalAppInfo localAppInfo) {
                                        mCachedLocalCachedDatas.put(localAppInfo.id, localAppInfo);
                                    }
                                })
                                .toList();
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        mCacheIsDirty = false;
                    }
                });
    }

    @Override
    public Observable<LocalAppInfo> getAppInfo(@NonNull final int appId) {
        if (mCachedLocalCachedDatas == null) {
            mCachedLocalCachedDatas = new LinkedHashMap<>();
        }
        LocalAppInfo cachedAppInfo = getAppInfoWithId(appId);
        // Respond immediately with cache if available
        if (cachedAppInfo != null) {
            return Observable.just(cachedAppInfo);
        } else {
            return mRemoteDataSource.getAppInfo(appId).doOnNext(new Action1<LocalAppInfo>() {
                @Override
                public void call(LocalAppInfo appInfo) {
                    mCachedLocalCachedDatas.put(appInfo.id, appInfo);
                }
            });
        }
    }

    @Override
    public void refreshAppInfos() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteAppInfo(@NonNull int appId) {
        mRemoteDataSource.deleteAppInfo(appId);
        mCachedLocalCachedDatas.remove(appId);
    }

    @Override
    public void saveAppInfo(LocalAppInfo appInfo) {
        mRemoteDataSource.saveAppInfo(appInfo);
        if (mCachedLocalCachedDatas == null) {
            mCachedLocalCachedDatas = new LinkedHashMap<>();
        }
        mCachedLocalCachedDatas.put(appInfo.id, appInfo);
    }

    @Nullable
    private LocalAppInfo getAppInfoWithId(@NonNull int id) {
        if (mCachedLocalCachedDatas == null || mCachedLocalCachedDatas.isEmpty()) {
            return null;
        } else {
            return mCachedLocalCachedDatas.get(id);
        }
    }
}
