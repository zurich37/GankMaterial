package com.zurich.gankmaterial.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.base.BaseListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullToRefreshLayout extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {

    public static final int ACTION_PULL_TO_REFRESH = 1;
    public static final int ACTION_LOAD_MORE_REFRESH = 2;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private OnRecyclerRefreshListener refreshListener;
    private int currentState;

    public PullToRefreshLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public PullToRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.widget_pull_to_refresh, this, true);
        ButterKnife.bind(this);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setItemDecoration(RecyclerView.ItemDecoration decoration) {
        recyclerView.addItemDecoration(decoration);
    }

    public void setOnRefreshListener(OnRecyclerRefreshListener listener) {
        this.refreshListener = listener;
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void setRefreshing() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    public <T> void setAdapter(BaseListActivity.BaseListAdapter baseAdapter) {
        recyclerView.setAdapter(baseAdapter);
    }

    @Override
    public void onRefresh() {
        if (refreshListener != null) {
            currentState = ACTION_PULL_TO_REFRESH;
            refreshListener.onRefresh(ACTION_PULL_TO_REFRESH);
        }
    }

    public void onRefreshCompleted() {
        if (refreshListener != null) {
            switch (currentState) {
                case ACTION_PULL_TO_REFRESH:
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case ACTION_LOAD_MORE_REFRESH:
                    break;
                default:
                    break;
            }
        }
    }

    public interface OnRecyclerRefreshListener {
        void onRefresh(int action);
    }
}
