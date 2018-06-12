package com.example.fangli.mymvpdemo.ui.presenter;

import android.util.Log;

import com.example.fangli.mymvpdemo.model.LearnResponseModel;
import com.example.fangli.mymvpdemo.ui.base.BasePresenter;
import com.example.fangli.mymvpdemo.view.IMainView;

import rx.Subscriber;

/**
 * Created by chenyu.
 * Created on 下午3:10 2018/6/6.
 * Author'github https://github.com/PrettyAnt
 * 获取数据的presenter
 */

public class MainPresenter extends BasePresenter<IMainView> {
    public MainPresenter(IMainView view) {
        super(view);
    }
    public void getGankData(int num,int page){
        addSubscription(mApiService.getVideoPath(num,page),
                new Subscriber<LearnResponseModel>() {
                    @Override
                    public void onCompleted() {
                        Log.i("wb", "数据加载完毕~");
                        mView.onComplete("数据加载完毕~");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("wb", "getGankData"+e.toString());
                        mView.onError("Error:"+e.getMessage());
                    }

                    @Override
                    public void onNext(LearnResponseModel learnResponseModel) {
                        Log.i("wb", "learnResponseModel"+learnResponseModel);
                        mView.onGetData(learnResponseModel);
                    }
                });
    }
}
