package com.zurich.gankmaterial.gankDatas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.base.BaseListActivity;
import com.zurich.gankmaterial.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * 示例Activity
 *
 * @author weixinfei
 */
public class SampleListActivity extends BaseListActivity<String> {

    @Override
    protected boolean parseIntent() {
        return true;
    }

    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.app_name);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        recycler.setRefreshing();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onRefresh(int action) {
        dataList = new ArrayList<>();
        dataList.clear();
        for (int i = 0; i < 50; i++) {
            dataList.add("sample list item " + i);
        }
        baseAdapter.notifyDataSetChanged();
        recycler.onRefreshCompleted();
    }

    class SampleViewHolder extends BaseViewHolder {

        TextView mSampleListItemLabel;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.text_sample_item);
        }

        @Override
        public void onBindViewHolder(int position) {
            mSampleListItemLabel.setText(dataList.get(position));
        }

    }
}
