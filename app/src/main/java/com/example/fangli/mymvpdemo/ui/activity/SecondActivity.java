package com.example.fangli.mymvpdemo.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fangli.mymvpdemo.R;
import com.example.fangli.mymvpdemo.model.LearnResponseModel;
import com.example.fangli.mymvpdemo.model.ResultsBean;
import com.example.fangli.mymvpdemo.ui.adapter.PeopleAdapter;
import com.example.fangli.mymvpdemo.ui.base.BaseActivity;
import com.example.fangli.mymvpdemo.ui.presenter.MainPresenter;
import com.example.fangli.mymvpdemo.ui.viewholder.BGANormalRefreshViewHolder;
import com.example.fangli.mymvpdemo.ui.viewholder.BGARefreshLayout;
import com.example.fangli.mymvpdemo.util.NetWorkUtils;
import com.example.fangli.mymvpdemo.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity<MainPresenter> implements IMainView<LearnResponseModel>, BGARefreshLayout.BGARefreshLayoutDelegate {


    private static  int PAGE = 0;
    @BindView(R.id.btn_second_show)
    Button mBtnSecondShow;
    @BindView(R.id.tv_nodate)
    TextView mTvNodate;
    @BindView(R.id.tv_second_state)
    TextView mTvsecondstate;
    @BindView(R.id.rv_second_list)
    RecyclerView mRvSecondList;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.bfl_fresh)
    BGARefreshLayout mBflFresh;
    private List<ResultsBean> results = new ArrayList<>();
    private PeopleAdapter mPeopleAdapter;
    private int NUM=8;

    @Override
    protected void initData() {

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initView() {
        mBflFresh.setDelegate(this);
        //1.设置布局管理器  spanCount==1时，GridLayoutManager布局管理器就类似于LinearLayoutManager
        final GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        mRvSecondList.setLayoutManager(gridLayoutManager);

        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, false);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.pull_refresh_bg);//背景色
        refreshViewHolder.setPullDownRefreshText(getString(R.string.refresh_pull_down_text));//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText(getString(R.string.refresh_release_text));//松开的提示文字
        refreshViewHolder.setRefreshingText(getString(R.string.refresh_ing_text));//刷新中的提示文字

        mBflFresh.setRefreshViewHolder(refreshViewHolder);
//        mBflFresh.shouldHandleAbsListViewLoadingMore(mRvSecondList);
//        //2.创建适配器
        // FIXME: 2018/6/6
        mPeopleAdapter = new PeopleAdapter(results, this, false);
        mRvSecondList.setAdapter(mPeopleAdapter);
        mRvSecondList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0//向下滑动
                 ) {
                    PAGE++;
                    mPresenter.getGankData(NUM, PAGE);
                }
                Log.i("wb", "加载中....");
            }
        });
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_second;
    }

    @Override
    public void onGetData(LearnResponseModel learnResponseModel) {
        List<ResultsBean> results = learnResponseModel.getResults();
        this.results.addAll(results);
    }

    @Override
    public void onComplete(String msg) {
        mBflFresh.endRefreshing();//结束刷新
        Toast.makeText(this, "msg" + msg, Toast.LENGTH_SHORT).show();
        mPeopleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {
        mBflFresh.endRefreshing();//结束刷新
        Toast.makeText(this, "msg" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (!NetWorkUtils.isNetworkAvailable(this)) {
            mTvsecondstate.setText(R.string.res_net_error);
            if (mBflFresh.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                mBflFresh.endRefreshing();
            }
        } else {
            mTvsecondstate.setText("secondactivity");
        }
        mPresenter.getGankData(8, 0);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        // BGARefresh的加载更多，不处理,使用到的是BaseRecyclerViewAdapterHelper的加载更多
        return false;
    }
    @OnClick(R.id.btn_second_show)
    public void onViewClicked(){
        Toast.makeText(this, "~~~~~~~ 点击了 ~~~~~~~~", Toast.LENGTH_SHORT).show();
        results.clear();
        mPeopleAdapter.notifyDataSetChanged();
    }
}
