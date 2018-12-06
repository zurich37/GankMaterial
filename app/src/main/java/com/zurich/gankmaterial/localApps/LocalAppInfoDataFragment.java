package com.zurich.gankmaterial.localApps;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.base.BaseFragment;
import com.zurich.gankmaterial.data.LocalAppInfo;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by weixinfei on 2016/12/8.
 */

public class LocalAppInfoDataFragment extends BaseFragment implements LocalAppInfoDataContact.View {
    private static final String TAG = "LocalAppFragment";
    private LocalAppInfoDataContact.Presenter mPresenter;
    public static LocalAppInfoDataFragment newInstance(Bundle bundle) {
        return new LocalAppInfoDataFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
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
    public void showDatas(List<LocalAppInfo> datas) {
        Log.d(TAG, "data.size:"+datas.size());
        datas.size();
    }

    @Override
    public void setPresenter(LocalAppInfoDataContact.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showNetError(View.OnClickListener onClickListener) {

    }
}
