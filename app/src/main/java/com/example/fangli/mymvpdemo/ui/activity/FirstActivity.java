package com.example.fangli.mymvpdemo.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fangli.mymvpdemo.R;
import com.example.fangli.mymvpdemo.imp.ItemClickListener;
import com.example.fangli.mymvpdemo.model.LearnResponseModel;
import com.example.fangli.mymvpdemo.model.ResultsBean;
import com.example.fangli.mymvpdemo.ui.adapter.PeopleAdapter;
import com.example.fangli.mymvpdemo.ui.base.BaseActivity;
import com.example.fangli.mymvpdemo.ui.presenter.MainPresenter;
import com.example.fangli.mymvpdemo.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FirstActivity extends BaseActivity<MainPresenter> implements IMainView<LearnResponseModel>, ItemClickListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.btn_show)
    Button mBtnShow;
    @BindView(R.id.rv_showList)
    RecyclerView mRvShowList;
    @BindView(R.id.srf_fresh)
    SwipeRefreshLayout mSrfFresh;
    @BindView(R.id.tv_nodate)
    TextView mTvNodate;
    private List<ResultsBean> results = new ArrayList<>();
    private PeopleAdapter peopleAdapter;
    private LearnResponseModel learnResponseModel;
    private int NUM = 20;
    private int PAGE = 0;
    private boolean isLoading = false,//正在加载
            refreshing = false;//正在刷新


    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_first;
    }

    @Override
    protected void initView() {
        //1.设置布局管理器  spanCount==1时，GridLayoutManager布局管理器就类似于LinearLayoutManager
        final GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        mRvShowList.setLayoutManager(gridLayoutManager);
//        //2.创建适配器
        peopleAdapter = new PeopleAdapter(results, this,false);// FIXME: 2018/6/6
        mRvShowList.setAdapter(peopleAdapter);
        peopleAdapter.notifyDataSetChanged();
        peopleAdapter.setItemClickListener(this);
        mSrfFresh.setOnRefreshListener(this);
        mRvShowList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                refreshing = mSrfFresh.isRefreshing();
                int lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                if (dy > 0//向下滑动
                        && (lastVisibleItemPosition + 1) == peopleAdapter.getItemCount()
                        && !refreshing
                        && !isLoading) {
                    PAGE++;
                    mPresenter.getGankData(NUM, PAGE);
                }
                Log.i("wb", "加载中....");
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getGankData(NUM, PAGE);
    }


    @OnClick({R.id.btn_show, R.id.tv_nodate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_show:
                results.clear();
                peopleAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_nodate:
                onRefresh();
                break;
        }
    }

    @Override
    public void onGetData(LearnResponseModel learnResponseModel) {
        refreshing = true;
        isLoading = true;
        this.learnResponseModel = learnResponseModel;
        results.addAll(learnResponseModel.getResults());
        peopleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onComplete(String msg) {
        mSrfFresh.setRefreshing(false);
        mTvNodate.setVisibility(View.GONE);
        refreshing = false;
        isLoading = false;
        Toast.makeText(this, "FirstActivity:" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String msg) {
        mSrfFresh.setRefreshing(false);
        mTvNodate.setVisibility(View.VISIBLE);
        results.clear();
        Toast.makeText(this, "FirstActivity:" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int postion) {
        switch (view.getId()) {
            case R.id.tv_name:
                Toast.makeText(this, "点击了:" + ((TextView) view).getText() + "-------" + postion, Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_time:
                Toast.makeText(this, "点击了:" + ((TextView) view).getText() + "-------" + postion, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onRefresh() {
        results.clear();
        mPresenter.getGankData(NUM, 0);
    }

}
