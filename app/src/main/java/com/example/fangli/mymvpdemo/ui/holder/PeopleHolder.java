package com.example.fangli.mymvpdemo.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fangli.mymvpdemo.R;
import com.example.fangli.mymvpdemo.imp.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenyu.
 * Created on 下午6:04 2018/5/29.
 * Author'github https://github.com/PrettyAnt
 */

public class PeopleHolder extends RecyclerView.ViewHolder {

    ItemClickListener itemClickListener;
    @BindView(R.id.tv_name)
    public TextView mTvName;
    @BindView(R.id.tv_desc)
    public TextView mTvdesc;
    @BindView(R.id.tv_time)
    public TextView mTvTime;
    @BindView(R.id.tv_url)
    public TextView mTvUrl;
    @BindView(R.id.ll_item)
    public LinearLayout mLlItem;
    private SparseArray<View> mViewSparseArray;
    private  View mConvertView;

    private PeopleHolder(View itemView) {
        super(itemView);
        this.mConvertView = itemView;
        ButterKnife.bind(this, itemView);
        mViewSparseArray = new SparseArray<>();
    }

    private PeopleHolder(View itemView,ItemClickListener itemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemClickListener = itemClickListener;
    }
    public static PeopleHolder create(View itemView,ItemClickListener itemClickListener){
        return new PeopleHolder(itemView,itemClickListener);
    }

    public static PeopleHolder create(View itemView) {
        return new PeopleHolder(itemView);
    }

    public <T extends View> T getView(int viewId) {
        View view = mViewSparseArray.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    @OnClick({R.id.tv_time,R.id.tv_name})
    public void onViewClicked(View view) {
        if (itemClickListener!=null) {
            itemClickListener.onItemClick(view,getAdapterPosition());
        }
    }
    //R.id.tv_name, R.id.tv_desc}
}
