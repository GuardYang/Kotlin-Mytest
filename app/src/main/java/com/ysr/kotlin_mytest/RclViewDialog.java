package com.ysr.kotlin_mytest;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

/**
 * Created by ysr on 2017/7/27 14:42.
 * 邮箱 ysr200808@163.com
 */

public class RclViewDialog extends Dialog implements OrderMenuAdapter.onItemClickListener {

    private final Context mContext;
    private RecyclerView mListView;
    private List<Data> datas;
    private OrderMenuAdapter orderMenuAdapter;

    public RclViewDialog(Context context, List<Data> datas) {
        super(context);
        mContext = context;
        this.datas = datas;
        initView();
        initListView();
    }

    private void initView() {
        View contentView = View.inflate(mContext, R.layout.content_dialog, null);
        mListView = contentView.findViewById(R.id.lv);
        setContentView(contentView);
    }

    private void initListView() {
        orderMenuAdapter = new OrderMenuAdapter(mContext, datas);
        orderMenuAdapter.setItemClickListener(this);
        mListView.setLayoutManager(new LinearLayoutManager(mContext));
        mListView.setAdapter(orderMenuAdapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        setHeight();
    }

    private void setHeight() {
        Window window = getWindow();
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (window.getDecorView().getHeight() >= (int) (displayMetrics.heightPixels * 0.8)) {
            attributes.height = (int) (displayMetrics.heightPixels * 0.8);
        }
        window.setAttributes(attributes);
    }


    @Override
    public void onItemTextClick(View view, int position, Data data) {
        itemClickListener.onItemClick(data);
        dismiss();
        cancel();

    }

    private onItemClick itemClickListener;

    public void setItemClickListener(onItemClick itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    interface onItemClick {
        void onItemClick(Data data);
    }
}
