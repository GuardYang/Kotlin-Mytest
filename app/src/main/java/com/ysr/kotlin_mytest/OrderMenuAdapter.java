package com.ysr.kotlin_mytest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ysr on 2017/7/27 13:54.
 * 邮箱 ysr200808@163.com
 */

public class OrderMenuAdapter extends RecyclerView.Adapter {
    private List<Data> list;
    private Context context;
    private String type;
    public OrderMenuAdapter(Context context, List<Data> list,String type) {
        this.list = list;
        this.type = type;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final int adjPosition = position;
        ((MyViewHolder) holder).textView.setText(list.get(position).getType());
        if (itemClickListener != null) {
            ((MyViewHolder) holder).textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
                    if (tag instanceof Data) {
                        itemClickListener.onItemTextClick(holder.itemView, adjPosition, (Data) tag,type);
                    }

                }
            });
            Data one = list.get(adjPosition);
            if (null != one) {
                ((MyViewHolder) holder).textView.setTag(one);
            }

        }

    }

    @Override
    public int getItemCount() {
        return   list == null ? 0:list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;


        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    private onItemClickListener itemClickListener;

    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 点击事件选择回调
     */
    interface onItemClickListener {
        void onItemTextClick(View view, int position, Data data,String type);
    }
}
