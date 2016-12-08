package com.zurich.gankmaterial.contract;

import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.presenter.BasePresenter;
import com.zurich.gankmaterial.view.BaseView;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 * Created by weixinfei on 2016/11/28.
 */
public interface AndroidContract {
    interface View extends BaseView<Presenter>{
        void showDatas(List<GankData> datas);
    }

    interface Presenter extends BasePresenter{
        void loadDatas(boolean mFirstLoad);

        void openDetail(GankData data);
    }
}
