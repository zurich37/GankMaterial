package com.zurich.gankmaterial.retrofit;

import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.retrofit.http.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 网络请求接口
 * Created by weixinfei on 16/7/4.
 */
interface GankService {

    /**
     * Gan.io随机数据请求接口
     *
     * @param type
     * @param size
     * @return
     */
    @GET("random/data/{type}/{size}")
    Observable<HttpResult<List<GankData>>> getGankRandomDatas(
            @Path("type") String type, @Path("size") int size);

    /**
     * Gan.io普通分类数据请求接口
     *
     * @param type
     * @param size
     * @return
     */
    @GET("data/{type}/{size}/{page}")
    Observable<HttpResult<List<GankData>>> getGankDatas(
            @Path("type") String type, @Path("size") int size, @Path("page") int page
    );
}
