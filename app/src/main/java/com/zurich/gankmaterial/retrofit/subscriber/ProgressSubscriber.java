package com.zurich.gankmaterial.retrofit.subscriber;


import android.util.Log;

import rx.Subscriber;

/**
 * 封装Subscriber
 * Created by weixinfei on 16/9/5.
 */
public abstract class ProgressSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        Log.d("ProgressSubscriber", "Request completed");
    }

    @Override
    public abstract void onError(Throwable e);

    @Override
    public abstract void onNext(T o);
}
