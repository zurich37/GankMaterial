package com.zurich.gankmaterial.gankDetail;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.gankDatas.viewholder.WalfareDataViewHolder;

/**
 * Created by weixinfei on 2016/11/28.
 */
public class WelfareDataAdapter extends BaseQuickAdapter<GankData, WalfareDataViewHolder> {

    WelfareDataAdapter() {
        super(R.layout.list_item_welfare_model);
    }

    @Override
    protected void convert(WalfareDataViewHolder holder, GankData gankData) {
        holder.ivIcon.displayFromUrlWithoutCorner(gankData.url);
    }
}
