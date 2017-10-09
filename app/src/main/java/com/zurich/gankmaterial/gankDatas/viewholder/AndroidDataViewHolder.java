package com.zurich.gankmaterial.gankDatas.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.widget.GankImageView;

/**
 * Created by weixinfei on 2016/11/28.
 */
public class AndroidDataViewHolder extends BaseViewHolder {
    public LinearLayout llInfoView;
    public TextView tvCreatTime;
    public TextView tvAuthor;
    public TextView tvTitle;
    public GankImageView ivIcon;

    public AndroidDataViewHolder(View itemView) {
        super(itemView);
        llInfoView = (LinearLayout) itemView.findViewById(R.id.ll_gank_model_info);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_gank_model_title);
        ivIcon = (GankImageView) itemView.findViewById(R.id.iv_gank_model_icon);
        tvCreatTime = (TextView) itemView.findViewById(R.id.tv_gank_creat_time);
        tvAuthor = (TextView) itemView.findViewById(R.id.tv_gank_author);
    }
}
