package com.zurich.gankmaterial.gankDatas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.base.BaseFragment;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.widget.CustomLoadMoreView;
import com.zurich.gankmaterial.widget.HintView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 各Gank.io数据页面
 * Created by weixinfei on 2016/11/27.
 */
public class GankDataFragment extends BaseFragment implements GankDatasContract.View {

    @BindView(R.id.rv_android_list)
    RecyclerView recyclerView;
    @BindView(R.id.hint_android_list_hint)
    HintView hintView;
    private GankDatasContract.Presenter mPresenter;
    private GankDataAdapter gankDataAdapter;

    public static Fragment newInstance(int i) {

        return new GankDataFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        gankDataAdapter = new GankDataAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        gankDataAdapter.setLoadMoreView(new CustomLoadMoreView());
        gankDataAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadDatas(false);
            }
        }, recyclerView);
        recyclerView.setAdapter(gankDataAdapter);

        gankDataAdapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void showDatas(List<GankData> datas) {
        if (!datas.isEmpty()) {
            hintView.hidden();
            gankDataAdapter.setNewData(datas);
        }
    }

    @Override
    public void showMoreDatas(List<GankData> datas) {
        if (!datas.isEmpty()) {
            hintView.hidden();
            gankDataAdapter.addData(datas);
            if (datas.size() == 20) {
                gankDataAdapter.loadMoreComplete();
            } else {
                gankDataAdapter.loadMoreEnd();
            }
        }
    }

    @Override
    public void setPresenter(GankDatasContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading(String msg) {
        hintView.loading().show();
    }

    @Override
    public void hideLoading() {
        if (hintView.isShowing()) {
            hintView.hidden();
        }
    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener) {
        hintView.empty(msg).show();
    }

    @Override
    public void showNetError(View.OnClickListener onClickListener) {

    }
}
