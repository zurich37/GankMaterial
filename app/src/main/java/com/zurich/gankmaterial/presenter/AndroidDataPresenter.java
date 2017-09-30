package com.zurich.gankmaterial.presenter;

import android.support.annotation.NonNull;

import com.zurich.gankmaterial.contract.AndroidDataContract;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.data.source.GankDataRepository;
import com.zurich.gankmaterial.fragment.GankDataFragment;
import com.zurich.gankmaterial.retrofit.subscriber.ProgressSubscriber;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link GankDataFragment}), retrieves the data and updates the
 * UI as required.
 * Created by weixinfei on 2016/11/28.
 */
public class AndroidDataPresenter implements AndroidDataContract.Presenter {
    @NonNull
    private final GankDataRepository mGanksRepository;

    @NonNull
    private final AndroidDataContract.View mView;

    private boolean mFirstLoad = true;

    @NonNull
    private CompositeSubscription mSubscriptions;

    public AndroidDataPresenter(@NonNull GankDataRepository ganksRepository, @NonNull AndroidDataContract.View view) {
        this.mGanksRepository = ganksRepository;
        this.mView = view;
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void loadDatas(boolean isForceUpdate) {
        loadGankDatas(mFirstLoad || isForceUpdate, mFirstLoad);
        this.mFirstLoad = false;
    }

    private void loadGankDatas(boolean isForceUpdate, boolean isShowUILoading) {
        if (isShowUILoading) {
            mView.showLoading("加载中");
        }
        if (isForceUpdate) {
            mGanksRepository.refreshDatas();
        }
        mSubscriptions.clear();
        Subscription subscribe = mGanksRepository
                .getGankDatas()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<List<GankData>>() {
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage(), null);
                    }

                    @Override
                    public void onNext(List<GankData> o) {
                        processDatas(o);
                    }
                });
        mSubscriptions.add(subscribe);
    }

    private void processDatas(@NonNull List<GankData> datas) {
        if (datas.isEmpty()) {
            processEmpty();
        } else {
            mView.showDatas(datas);
        }
    }

    private void processEmpty() {
        mView.hideLoading();
        mView.showEmpty("无内容", null);
    }

    @Override
    public void openDetail(GankData data) {

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
