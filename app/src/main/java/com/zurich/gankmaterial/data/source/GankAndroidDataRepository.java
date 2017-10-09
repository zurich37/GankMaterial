package com.zurich.gankmaterial.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zurich.gankmaterial.GankConstans;

/**
 * Android数据仓库
 * Created by weixinfei on 2017/10/9.
 */

public class GankAndroidDataRepository extends GankDataRepository {

    @Nullable
    private static GankDataRepository INSTANCE = null;

    private GankAndroidDataRepository(@NonNull GankDataSource dataSource) {
        super(dataSource);
    }

    public static GankDataRepository getInstance(@NonNull GankDataSource dataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GankAndroidDataRepository(dataSource);
        }
        return INSTANCE;
    }

    @Override
    protected String getRequestCode() {
        return GankConstans.REQUEST_TYPE_ANDROID;
    }
}
