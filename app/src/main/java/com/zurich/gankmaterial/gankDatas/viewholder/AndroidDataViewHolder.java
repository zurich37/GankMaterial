package com.zurich.gankmaterial.gankDatas.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zurich.gankmaterial.R;

/**
 * Created by weixinfei on 2016/11/28.
 */
public class AndroidDataViewHolder extends BaseViewHolder {
    public TextView tvCreatTime;
    public TextView tvAuthor;
    public TextView tvTitle;
    public ImageView ivIcon;

    public AndroidDataViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_gank_model_title);
        ivIcon = (ImageView) itemView.findViewById(R.id.iv_gank_model_icon);
        tvCreatTime = (TextView) itemView.findViewById(R.id.tv_gank_creat_time);
        tvAuthor = (TextView) itemView.findViewById(R.id.tv_gank_author);
    }
}
