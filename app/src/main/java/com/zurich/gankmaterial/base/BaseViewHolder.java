package com.zurich.gankmaterial.base;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * 基础ViewHolder
 * @author weixinfei
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder(int position);


}