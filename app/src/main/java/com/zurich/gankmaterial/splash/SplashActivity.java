package com.zurich.gankmaterial.splash;

import android.content.Intent;
import android.os.Bundle;

import com.zurich.gankmaterial.AppStatusTracker;
import com.zurich.gankmaterial.GankConstans;
import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.base.BaseActivity;
import com.zurich.gankmaterial.mainPage.MainActivity;
import com.zurich.gankmaterial.retrofit.subscriber.ProgressSubscriber;
import com.zurich.gankmaterial.util.schedulers.SchedulerProvider;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppStatusTracker.getInstance().setAppStatus(GankConstans.STATUS_OFFLINE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean parseIntent() {
        return true;
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_splash, 0, BaseActivity.MODE_NONE);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {
        Observable.just(0)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .subscribe(new ProgressSubscriber<Integer>() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer o) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
