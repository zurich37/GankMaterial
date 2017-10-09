package com.zurich.gankmaterial.view;

import android.view.View;

import rx.Observable;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showLoading(String msg);

    void hideLoading();

    void showError(String msg, View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener);

    void showNetError(View.OnClickListener onClickListener);

    <T> Observable.Transformer<T, T> bindToLifecycle();
}
