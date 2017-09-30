package com.zurich.gankmaterial.gankDatas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.gankDatas.viewholder.AndroidDataViewHolder;

import java.util.List;

/**
 * Created by weixinfei on 2016/11/28.
 */
public class GankDataAdapter extends RecyclerView.Adapter<AndroidDataViewHolder> {
    private List<GankData> datas;
    public GankDataAdapter(List<GankData> datas) {
        this.datas = datas;
    }

    @Override
    public AndroidDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gank_model, parent, false);
        return new AndroidDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AndroidDataViewHolder holder, int position) {
        GankData gankData = datas.get(position);
        holder.tvTitle.setText(gankData.desc);
        holder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        holder.tvAuthor.setText(gankData.who);
        String creatTime = gankData.createdAt.replace("T", " ").replace("Z", " ");
        holder.tvCreatTime.setText(creatTime);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<GankData> datas) {
        if (datas != null) {
            this.datas = datas;
            notifyDataSetChanged();
        }
    }
}
