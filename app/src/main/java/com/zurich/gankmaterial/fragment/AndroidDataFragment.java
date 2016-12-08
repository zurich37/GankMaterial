package com.zurich.gankmaterial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.adapter.AndroidDataAdapter;
import com.zurich.gankmaterial.contract.AndroidContract;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.widget.HintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by weixinfei on 2016/11/27.
 */
public class AndroidDataFragment extends BaseFragment implements AndroidContract.View {

    @BindView(R.id.rv_android_list)
    RecyclerView recyclerView;
    @BindView(R.id.hint_android_list_hint)
    HintView hintView;
    private AndroidContract.Presenter mPresenter;
    private List<GankData> mList;
    private AndroidDataAdapter mAdapter;

    public static Fragment newInstance(int i) {

        return new AndroidDataFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mAdapter = new AndroidDataAdapter(new ArrayList<GankData>(0));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
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
    public void setPresenter(AndroidContract.Presenter presenter) {
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
    public void showEmpty(String msg, View.OnClickListener onClickListener, int imageId) {
        hintView.empty(msg).show();
    }

    @Override
    public void showNetError(View.OnClickListener onClickListener) {

    }
}
