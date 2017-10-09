package com.zurich.gankmaterial.gankDatas;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.gankDatas.viewholder.AndroidDataViewHolder;

/**
 * Created by weixinfei on 2016/11/28.
 */
public class GankDataAdapter extends BaseQuickAdapter<GankData, AndroidDataViewHolder> {


    public GankDataAdapter() {
        super(R.layout.list_item_gank_model);
    }

    @Override
    protected void convert(AndroidDataViewHolder holder, GankData gankData) {
        holder.tvTitle.setText(gankData.desc);
        holder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        holder.tvAuthor.setText(gankData.who);
        String creatTime = gankData.createdAt.replace("T", " ").replace("Z", " ");
        holder.tvCreatTime.setText(creatTime);
    }
}
