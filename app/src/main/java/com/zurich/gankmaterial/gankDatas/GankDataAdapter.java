package com.zurich.gankmaterial.gankDatas;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.gankDatas.viewholder.AndroidDataViewHolder;

/**
 * Created by weixinfei on 2016/11/28.
 */
public class GankDataAdapter extends BaseQuickAdapter<GankData, AndroidDataViewHolder> {

    private Context context;

    GankDataAdapter(Context context) {
        super(R.layout.list_item_gank_model);
        this.context = context;
    }

    @Override
    protected void convert(AndroidDataViewHolder holder, GankData gankData) {
        if (gankData.images != null && gankData.images.get(0) != null) {
            holder.ivIcon.setVisibility(View.VISIBLE);
            holder.ivIcon.displayFromUrl(gankData.images.get(0));
            holder.llInfoView.setBackgroundColor(context.getResources().getColor(R.color.transparent_bg));
            holder.tvAuthor.setTextColor(context.getResources().getColor(R.color.text_gray));
            holder.tvCreatTime.setTextColor(context.getResources().getColor(R.color.text_gray));
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.ivIcon.setVisibility(View.GONE);
            holder.llInfoView.setBackgroundColor(Color.TRANSPARENT);
            holder.tvAuthor.setTextColor(context.getResources().getColor(R.color.text_normal_subtitle));
            holder.tvCreatTime.setTextColor(context.getResources().getColor(R.color.text_normal_subtitle));
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.text_normal_title));
        }
        holder.tvTitle.setText(gankData.desc);
        holder.tvAuthor.setText(gankData.who);
        String creatTime = gankData.createdAt.replace("T", " ").replace("Z", " ");
        holder.tvCreatTime.setText(creatTime);
    }
}
