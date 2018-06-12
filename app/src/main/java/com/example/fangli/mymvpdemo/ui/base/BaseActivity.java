package com.example.fangli.mymvpdemo.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * Created by chenyu.
 * Created on 下午2:42 2018/6/6.
 * Author'github https://github.com/PrettyAnt
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private Unbinder bind;
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        setContentView(provideContentViewId());
        bind = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract T createPresenter();

    protected abstract void initView();

    public abstract int provideContentViewId();
    protected void registerEventBus(Object subscribe) {
        if (!isEventBusRegisted(subscribe)) {
            EventBus.getDefault().register(subscribe);
        }
    }

    protected void unregisterEventBus(Object subscribe) {
        if (isEventBusRegisted(subscribe)) {
            EventBus.getDefault().unregister(subscribe);
        }
    }

    private boolean isEventBusRegisted(Object subscribe) {
        return EventBus.getDefault().isRegistered(subscribe);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {//取消注册RxJava
            mPresenter.detachView();
        }
        bind.unbind();
    }
}
