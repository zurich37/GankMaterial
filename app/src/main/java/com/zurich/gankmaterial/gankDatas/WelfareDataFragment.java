package com.zurich.gankmaterial.gankDatas;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
public class WelfareDataFragment extends BaseFragment implements GankDatasContract.View {

    @BindView(R.id.rv_android_list)
    RecyclerView recyclerView;
    @BindView(R.id.hint_android_list_hint)
    HintView hintView;
    private GankDatasContract.Presenter mPresenter;
    private WelfareDataAdapter gankDataAdapter;

    public static Fragment newInstance(int i) {

        return new WelfareDataFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        gankDataAdapter = new WelfareDataAdapter();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        gankDataAdapter.setLoadMoreView(new CustomLoadMoreView());
        gankDataAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadDatas(false);
            }
        }, recyclerView);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        recyclerView.setAdapter(gankDataAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        gankDataAdapter.disableLoadMoreIfNotFullPage();

        gankDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GankData gankData = (GankData) adapter.getData().get(position);
                WelfareDetailActivity.launchActivity(getActivity(), gankData);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
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

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.top = space;
            outRect.bottom = space;
            outRect.left = space;
            outRect.right = space;
        }
    }
}
