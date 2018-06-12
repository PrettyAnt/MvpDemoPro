package com.example.fangli.mymvpdemo.view;


import com.example.fangli.mymvpdemo.model.LearnResponseModel;

/**
 * Created by chenyu.
 * Created on 下午5:15 2018/5/29.
 * Author'github https://github.com/PrettyAnt
 */

public interface IMainView {
    public void onGetData(LearnResponseModel learnResponseModel);
    public void onComplete(String msg);
    public void onError(String msg);
}
