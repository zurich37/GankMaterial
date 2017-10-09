package com.zurich.gankmaterial.retrofit.http;


import com.zurich.gankmaterial.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 创建OkHttpClient
 * Created by weixinfei on 16/9/4.
 */
public enum OKHttpFactory {
    INSTANCE;

    private final OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 25;
    private static final int TIMEOUT_CONNECTION = 25;

    OKHttpFactory() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //失败重连
        //打印日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.retryOnConnectionFailure(true)
                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse
                        .newBuilder()
                        .build();
            }
        });

        //增加拦截器
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor);
        }
        okHttpClient = builder.build();

    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
