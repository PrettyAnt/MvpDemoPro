package com.example.fangli.mymvpdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fangli.mymvpdemo.R;
import com.example.fangli.mymvpdemo.ui.activity.FirstActivity;
import com.example.fangli.mymvpdemo.ui.activity.SecondActivity;
import com.example.fangli.mymvpdemo.ui.activity.ThirdActivity;
import com.example.fangli.mymvpdemo.ui.base.BaseActivity;
import com.example.fangli.mymvpdemo.ui.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> {

    @BindView(R.id.tv_first)
    TextView mTvFirst;
    @BindView(R.id.tv_second)
    TextView mTvSecond;
    @BindView(R.id.tv_thrid)
    TextView mTvThrid;
    @BindView(R.id.tv_fourth)
    TextView mTvFourth;

    @Override
    protected void initData() {

    }

    @Override
    protected MainPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.tv_first, R.id.tv_second, R.id.tv_thrid, R.id.tv_fourth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_first:
                startActivity(FirstActivity.class,this);
                break;
            case R.id.tv_second:
                startActivity(SecondActivity.class,this);
                break;
            case R.id.tv_thrid:
                startActivity(ThirdActivity.class,this);
                break;
            case R.id.tv_fourth:
                Toast.makeText(this,getString(R.string.cy_function_notopen),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void  startActivity(Class<?extends BaseActivity> clazz, Context context) {
        Intent intent = new Intent(context, clazz);
        startActivity(intent);
    }
}
