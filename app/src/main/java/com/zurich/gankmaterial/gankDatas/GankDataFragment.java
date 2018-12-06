package com.zurich.gankmaterial.gankDatas;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.legacy.widget.Space;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.base.BaseFragment;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.util.DimenUtils;
import com.zurich.gankmaterial.webPage.GankDataDetailActivity;
import com.zurich.gankmaterial.widget.CustomLoadMoreView;
import com.zurich.gankmaterial.widget.HintView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 各Gank.io数据页面
 *
 * @author weixinfei
 * @date 2016/11/27
 */
public class GankDataFragment extends BaseFragment implements GankDatasContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_android_list)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_main_view)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.hint_android_list_hint)
    HintView hintView;
    private GankDatasContract.Presenter mPresenter;
    private GankDataAdapter gankDataAdapter;

    public static Fragment newInstance() {
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

        Space space = new Space(getContext());
        space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DimenUtils.dp2px(getContext(), 15)));
        gankDataAdapter.addHeaderView(space);
        gankDataAdapter.disableLoadMoreIfNotFullPage();

        gankDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GankData gankData = (GankData) adapter.getData().get(position);
                GankDataDetailActivity.launchActivity(getActivity(), gankData);
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.g_green, R.color.g_red, R.color.g_yellow, R.color.g_blue);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.subscribe();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }

    @Override
    public void showDatas(List<GankData> datas) {
        if (!datas.isEmpty()) {
            hintView.hidden();
            swipeRefreshLayout.setRefreshing(false);
            gankDataAdapter.setNewData(datas);
        }
    }

    @Override
    public void showMoreDatas(List<GankData> datas) {
        if (!datas.isEmpty()) {
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
        if (!swipeRefreshLayout.isRefreshing()) {
            hintView.loading().show();
        }
    }

    @Override
    public void hideLoading() {
        if (hintView.isShowing()) {
            hintView.hidden();
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {
        hintView.error(msg).show();
    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener) {
        hintView.empty(msg).show();
    }

    @Override
    public void showNetError(View.OnClickListener onClickListener) {

    }

    @Override
    public void onRefresh() {
        mPresenter.loadDatas(true);
    }
}
