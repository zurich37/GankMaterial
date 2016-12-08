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
     * 谷歌框架请求接口
     * @param type
     * @param size
     * @return
     */
    @GET("random/data/{type}/{size}")
    Observable<HttpResult<List<GankData>>> getGankRandomDatas(
            @Path("type") String type, @Path("size") int size);

    /**
     * 下载apk
     * @param fileUrl
     * @return
     *//*
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);

    *//**
     * Tools APP请求接口
     * @param param
     * @return
     *//*
    @FormUrlEncoded
    @POST("api/application/{type}/list")
    Observable<HttpResult<ToolsData>> getToolsApks(
            @Path("type") String type,
            @Field("uid") String uid,
            @Field("param") JSONObject param);

    *//**
     * 文章列表请求接口
     * @param param
     * @return
     *//*
    @FormUrlEncoded
    @POST("api/webview/list")
    Observable<HttpResult<List<ArticleData>>> getArticles(
            @Field("param") JSONObject param);

    *//**
     * 检查自身更新
     * @return
     *//*
    @GET("api/self/version")
    Observable<UpdateData> checkSelfUpdate();

    *//**
     * 获取修复成功次数
     * @return
     *//*
    @GET("api/repair/success")
    Observable<HttpResult<Integer>> getSuccessCount();

    *//**
     * 获取分享内容
     * @return
     *//*
    @GET("api/share/{type}")
    Observable<HttpResult<ShareContent>> getShareContent(
            @Path("type") String type
    );

    *//**
     * 获取brand Logo以及适配说明
     * @param param
     * @return
     *//*
    @FormUrlEncoded
    @POST("api/google/getBrandInfo")
    Observable<HttpResult<MatchLabelData>> getMatchLabelData(
            @Field("param") JSONObject param);

    *//**
     * 文章列表请求接口
     * @param param
     * @return
     *//*
    @FormUrlEncoded
    @POST("api/webview/list")
    Observable<HttpResult<List<ArticleData>>> getMatchArticles(
            @Field("param") JSONObject param);

    *//**
     * 获取用户信息以及解锁次数
     * @param uid
     * @return
     *//*
    @FormUrlEncoded
    @POST("api/account/getAccount")
    Observable<HttpResult<UserInfo>> getUserInfo(
            @Field("uid") String uid, @Field("version") String version);

    *//**
     * 记录解锁次数
     * @param uid
     * @return
     *//*
    @FormUrlEncoded
    @POST("api/account/addKeyNum")
    Observable<HttpResult<UserInfo>> markUnlockCount(
            @Field("uid") String uid, @Field("keyNum") int keyNum, @Field("applicationId") int applicationId);

    *//**
     * 记录下载成功
     *
     * @param uid
     * @param url
     * @return
     *//*
    @FormUrlEncoded
    @POST("download/success")
    Call<ResponseBody> markDownloadSuccess(
            @Field("url") String url, @Field("uid") String uid);

    *//**
     * 获取VPNServerIP
     * @return
     *//*
    @FormUrlEncoded
    @POST("api/dns/get")
    Observable<HttpResult<String>> getVPNServer(
            @Field("uid") String uid
    );*/
}
