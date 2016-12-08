package com.zurich.gankmaterial.retrofit;


import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.retrofit.http.ApiException;
import com.zurich.gankmaterial.retrofit.http.HttpResult;
import com.zurich.gankmaterial.retrofit.http.OKHttpFactory;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 网络请求API
 * Created by weixinfei on 16/7/4.
 */
public class HttpMethods {
    private static final String BASE_URL = "http://gank.io/api/";
    public static final String TAG = "HttpMethods";

    private GankService gankService;
    private static List<Call<ResponseBody>> downloadCalls;

    private HttpMethods() {
        downloadCalls = new ArrayList<>();
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
     * 添加线程管理并订阅
     *
     * @param o 观察者
     * @param s 订阅者
     */
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
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
     * 获取谷歌框架apk
     *
     * @param type 内容类型
     * @param size 请求数目
     */
    public Observable getGankRandomDataList(String type, int size) {
        Observable<List<GankData>> observable = gankService.getGankRandomDatas(type, size)
                .map(new HttpResultFunc<List<GankData>>());
        return observable;
    }
}
