package com.zurich.gankmaterial.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.widget.DividerItemDecoration;
import com.zurich.gankmaterial.widget.PullToRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 基础列表页面
 *
 * @author weixinfei
 */
public abstract class BaseListActivity<T> extends BaseActivity implements PullToRefreshLayout.OnRecyclerRefreshListener {

    @BindView(R.id.pullToRefreshRecycler)
    protected PullToRefreshLayout recycler;
    protected BaseListAdapter baseAdapter;
    protected ArrayList<T> dataList;

    @Override
    protected boolean parseIntent() {
        return false;
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_base_list, -1);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {
        recycler.setLayoutManager(getLayoutManager());
        recycler.setItemDecoration(getItemDecoration());
        recycler.setOnRefreshListener(this);
        baseAdapter = new BaseListAdapter();
        recycler.setAdapter(baseAdapter);
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getApplicationContext());
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(getApplicationContext(), R.drawable.list_divider);
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

    public class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent, viewType);
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
