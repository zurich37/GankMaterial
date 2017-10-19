package com.zurich.gankmaterial.retrofit;


import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.retrofit.http.ApiException;
import com.zurich.gankmaterial.retrofit.http.HttpResult;
import com.zurich.gankmaterial.retrofit.http.OKHttpFactory;
import com.zurich.gankmaterial.util.schedulers.SchedulerProvider;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * 网络请求API
 * Created by weixinfei on 16/7/4.
 */
public class HttpMethods {
    private static final String BASE_URL = "http://gank.io/api/";
    public static final String TAG = "HttpMethods";

    private GankService gankService;

    private HttpMethods() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(OKHttpFactory.INSTANCE.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        gankService = retrofit.create(GankService.class);
    }

    /**
     * 访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 添加线程管理
     *
     * @param o 观察者
     */
    private <T> Observable<T> bindSchedulers(Observable<T> o) {
        return o.subscribeOn(SchedulerProvider.getInstance().io())
                .unsubscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui());
    }

    /**
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (!httpResult.isResultCode()) {
                throw new ApiException(httpResult.isResultCode());
            }
            return httpResult.getResults();
        }
    }

    /**
     * Gank.io随机数据
     *
     * @param type 内容类型
     * @param size 请求数目
     */
    public Observable<List<GankData>> getGankRandomDataList(String type, int size) {
        return bindSchedulers(
                gankService.getGankRandomDatas(type, size).map(new HttpResultFunc<List<GankData>>())
        );
    }

    /**
     * Gank.io普通分类数据
     * @param type
     * @param size
     * @param page
     * @return
     */
    public Observable<List<GankData>> getGankDatas(String type, int size, int page) {
        return bindSchedulers(
                gankService.getGankDatas(type, size, page).map(new HttpResultFunc<List<GankData>>())
        );
    }
}
