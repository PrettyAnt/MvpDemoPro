package com.example.fangli.mymvpdemo.view;


/**
 * Created by chenyu.
 * Created on 下午5:15 2018/5/29.
 * Author'github https://github.com/PrettyAnt
 */

public interface IMainView<T> {
    public void onGetData(T t);
    public void onComplete(String msg);
    public void onError(String msg);
}
