package com.zurich.gankmaterial.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 基础列表页面
 *
 * @author weixinfei
 */
public abstract class BaseListActivity<T> extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    protected BaseListAdapter baseAdapter;
    protected ArrayList<T> dataList;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_base_list, -1);
    }

    @Override
    protected void setUpView() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void setUpData() {
        recyclerView.setLayoutManager(getLayoutManager());
        RecyclerView.ItemDecoration decoration = getItemDecoration();
        recyclerView.addItemDecoration(decoration);
        baseAdapter = new BaseListAdapter();
        recyclerView.setAdapter(baseAdapter);
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

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getApplicationContext());
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(getApplicationContext(), R.drawable.list_divider);
    }

    protected abstract BaseViewHolder getViewHodler(ViewGroup parent, int viewType);

    public class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return getViewHodler(parent, viewType);
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            holder.onBindViewHolder(position);
        }

        @Override
        public int getItemCount() {
            return dataList != null ? dataList.size() : 0;
        }

        @Override
        public int getItemViewType(int position) {
            return getItemType(position);
        }

        protected int getItemType(int position) {
            return 0;
        }
    }
}
