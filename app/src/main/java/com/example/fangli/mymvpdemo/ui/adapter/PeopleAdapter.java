package com.example.fangli.mymvpdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.fangli.mymvpdemo.R;
import com.example.fangli.mymvpdemo.imp.ItemClickListener;
import com.example.fangli.mymvpdemo.model.ResultsBean;
import com.example.fangli.mymvpdemo.ui.holder.PeopleHolder;

import java.util.List;

/**
 * Created by chenyu.
 * Created on 下午6:03 2018/5/29.
 * Author'github https://github.com/PrettyAnt
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleHolder> {

    ItemClickListener itemClickListener;
    private boolean isLoadMore;//是否加载更多
    private List<ResultsBean> peopleModelList;
    private Context context;
    private final int TYPE_FOOTER_VIEW=2<<3;

    public PeopleAdapter(List<ResultsBean> peopleModelList, Context context, boolean isLoadMore) {
        this.peopleModelList = peopleModelList;
        this.context = context;
        this.isLoadMore = isLoadMore;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public PeopleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PeopleHolder peopleHolder = null;
        switch (viewType) {
//            case TYPE_FOOTER_VIEW:
//                RelativeLayout footerLayout = new RelativeLayout(context);
//                peopleHolder = PeopleHolder.create(footerLayout);
//                break;
            default:
                peopleHolder=PeopleHolder.create(LayoutInflater
                                .from(context)
                                .inflate(R.layout.item_people_info, parent, false),
                        itemClickListener);
                break;
        }

        return peopleHolder;
    }

    @Override
    public void onBindViewHolder(PeopleHolder holder, int position) {
        holder.mTvName.setText(peopleModelList.get(position).getWho());
        holder.mTvdesc.setText(peopleModelList.get(position).getDesc());
        holder.mTvTime.setText(peopleModelList.get(position).getPublishedAt());
        holder.mTvUrl.setText(peopleModelList.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return peopleModelList.size();
    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (isFooterView(position)) {
//            return TYPE_FOOTER_VIEW;
//        }
//        return super.getItemViewType(position);
//    }

//    /**
//     * 是否加载更多
//     * @param position
//     * @return
//     */
//    private boolean isFooterView(int position) {
//        return isLoadMore && position >= getItemCount() - 1;
//    }


//    @Override
//    public void onViewAttachedToWindow(PeopleHolder holder) {
//        super.onViewAttachedToWindow(holder);
//        if (isFooterView(holder.getLayoutPosition())) {
//            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//
//            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
//                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
//                p.setFullSpan(true);
//            }
//        }
//    }

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//        if (layoutManager instanceof GridLayoutManager) {
//            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
//            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    if (isFooterView(position)) {
//                        return gridManager.getSpanCount();
//                    }
//                    return 1;
//                }
//            });
//        }
//    }
}
