package com.zurich.gankmaterial.gankDatas;

import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.data.source.GankDataRepository;
import com.zurich.gankmaterial.retrofit.subscriber.ProgressSubscriber;

import java.util.List;

import androidx.annotation.NonNull;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link GankDataFragment}), retrieves the data and updates the
 * UI as required.
 * Created by weixinfei on 2016/11/28.
 */
public class GankDatasPresenter implements GankDatasContract.Presenter {
    @NonNull
    private final GankDataRepository mGanksRepository;

    @NonNull
    private final GankDatasContract.View mView;

    private boolean mFirstLoad = true;

    private int startPage = 1;
    @NonNull
    private CompositeSubscription mSubscriptions;

    public GankDatasPresenter(@NonNull GankDataRepository ganksRepository, @NonNull GankDatasContract.View view) {
        this.mGanksRepository = ganksRepository;
        this.mView = view;
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void loadDatas(boolean isForceUpdate) {
        loadGankDatas(mFirstLoad || isForceUpdate, mFirstLoad, !isForceUpdate);
        this.mFirstLoad = false;
    }

    private void loadGankDatas(boolean isForceUpdate, boolean isShowUILoading, final boolean isLoadMore) {
        if (isShowUILoading) {
            mView.showLoading("加载中");
        }
        if (isForceUpdate) {
            startPage = 1;
            mGanksRepository.refreshDatas();
        }

        if (isLoadMore) {
            startPage++;
        }
        mSubscriptions.clear();
        Subscription subscribe = mGanksRepository
                .getGankDatas(null, startPage)
                .compose(mView.<List<GankData>>bindToLifecycle())
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
                        processDatas(o, isLoadMore);
                    }
                });
        mSubscriptions.add(subscribe);
    }

    private void processDatas(@NonNull List<GankData> datas, boolean isLoadMore) {
        mView.hideLoading();
        if (datas.isEmpty()) {
            processEmpty();
        } else {
            if (!isLoadMore) {
                mView.showDatas(datas);
            } else {
                mView.showMoreDatas(datas);
            }
        }
    }

    private void processEmpty() {
        mView.showEmpty("无内容", null);
    }

    @Override
    public void openDetail(GankData data) {

    }

    @Override
    public void subscribe() {
        loadDatas(true);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
