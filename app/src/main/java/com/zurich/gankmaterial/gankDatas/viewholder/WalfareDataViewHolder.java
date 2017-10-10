package com.zurich.gankmaterial.gankDatas.viewholder;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.widget.GankImageView;

/**
 * Created by weixinfei on 2017/10/9.
 */

public class WalfareDataViewHolder extends BaseViewHolder {
    public GankImageView ivIcon;

    public WalfareDataViewHolder(View itemView) {
        super(itemView);
        ivIcon = (GankImageView) itemView.findViewById(R.id.iv_welfare);
    }
}
