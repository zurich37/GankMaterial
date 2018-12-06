package com.zurich.gankmaterial.data.source;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zurich.gankmaterial.GankConstans;

/**
 * iOS数据仓库
 * Created by weixinfei on 2017/10/9.
 */

public class GankIOSDataRepository extends GankDataRepository {

    @Nullable
    private static GankDataRepository INSTANCE = null;

    private GankIOSDataRepository(@NonNull GankDataSource dataSource) {
        super(dataSource);
    }

    public static GankDataRepository getInstance(@NonNull GankDataSource dataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GankIOSDataRepository(dataSource);
        }
        return INSTANCE;
    }

    @Override
    protected String getRequestCode() {
        return GankConstans.REQUEST_TYPE_IOS;
    }
}
