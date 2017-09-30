package com.zurich.gankmaterial.contract;

import com.zurich.gankmaterial.data.LocalAppInfo;
import com.zurich.gankmaterial.presenter.BasePresenter;
import com.zurich.gankmaterial.view.BaseView;

import java.util.List;

/**
 * Created by weixinfei on 2016/12/8.
 */

public interface LocalAppInfoDataContact {
    interface View extends BaseView<LocalAppInfoDataContact.Presenter> {
        void showDatas(List<LocalAppInfo> datas);
    }

    interface Presenter extends BasePresenter {
        void loadDatas(boolean isForceRefresh);

        void openDetail(LocalAppInfo data);
    }
}
