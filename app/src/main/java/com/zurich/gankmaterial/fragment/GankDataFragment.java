package com.zurich.gankmaterial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.adapter.AndroidDataAdapter;
import com.zurich.gankmaterial.contract.AndroidDataContract;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.widget.HintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 各Gank.io数据页面
 * Created by weixinfei on 2016/11/27.
 */
public class GankDataFragment extends BaseFragment implements AndroidDataContract.View {

    @BindView(R.id.rv_android_list)
    RecyclerView recyclerView;
    @BindView(R.id.hint_android_list_hint)
    HintView hintView;
    private AndroidDataContract.Presenter mPresenter;
    private List<GankData> mList;
    private AndroidDataAdapter mAdapter;

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
        mAdapter = new AndroidDataAdapter(new ArrayList<GankData>(0));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
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
            mAdapter.setData(datas);
        }
    }

    @Override
    public void setPresenter(AndroidDataContract.Presenter presenter) {
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
