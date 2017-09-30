package com.zurich.gankmaterial.view;

import android.view.View;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showLoading(String msg);

    void hideLoading();

    void showError(String msg, View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener);

    void showNetError(View.OnClickListener onClickListener);
}
