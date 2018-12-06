package com.zurich.gankmaterial.localApps;

import androidx.annotation.NonNull;

import com.zurich.gankmaterial.data.LocalAppInfo;
import com.zurich.gankmaterial.data.source.LocalAppInfoRepository;
import com.zurich.gankmaterial.util.schedulers.BaseSchedulerProvider;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by weixinfei on 2016/12/8.
 */

public class LocalAppInfoDataPresenter implements LocalAppInfoDataContact.Presenter {

    @NonNull
    private final LocalAppInfoRepository mAppInfoRespository;

    @NonNull
    private final LocalAppInfoDataContact.View mView;

    private boolean mFirstLoad = true;

    @NonNull
    private CompositeSubscription mSubscriptions;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    public LocalAppInfoDataPresenter(@NonNull LocalAppInfoRepository mAppInfoRespository, @NonNull LocalAppInfoDataContact.View mView, @NonNull BaseSchedulerProvider mSchedulerProvider) {
        this.mAppInfoRespository = mAppInfoRespository;
        this.mView = mView;
        this.mSchedulerProvider = mSchedulerProvider;
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void loadDatas(boolean isForceRefresh) {
        loadAppInfoDatas(mFirstLoad || isForceRefresh);
    }

    @Override
    public void openDetail(LocalAppInfo data) {

    }

    private void loadAppInfoDatas(boolean isForceRefresh) {
        if (isForceRefresh) {
            mAppInfoRespository.refreshAppInfos();
        }
        mSubscriptions.clear();
        Subscription subscription = mAppInfoRespository
                .getAppInfos()
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Observer<List<LocalAppInfo>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage(), null);
                    }

                    @Override
                    public void onNext(List<LocalAppInfo> localAppInfos) {
                        mView.showDatas(localAppInfos);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void subscribe() {
        loadDatas(false);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
