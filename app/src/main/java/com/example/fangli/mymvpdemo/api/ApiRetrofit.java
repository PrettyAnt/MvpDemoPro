package com.example.fangli.mymvpdemo.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by chenyu.
 * Created on 上午11:54 2018/5/31.
 * Author'github https://github.com/PrettyAnt
 */

public class ApiRetrofit {
    private static ApiRetrofit sApiRetrofit;
    private final ApiService mApiService;
    private OkHttpClient mClient;
    private Retrofit mRetrofit;

//    public static <T> void waiMaiNetWorkUtil(String encryptStr,
//                                             String channelId,
//                                             final Class<T> t,
//                                             final Context context,
//                                             final int OrderType) {
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .build();
//        retrofit = new Retrofit
//                .Builder()
//                .baseUrl("")//获取baseUrl
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//        ApiService httpApi = retrofit.create(ApiService.class);
//        Call<String> stringCall = httpApi.waiMaiNetMethod(encryptStr, channelId);
//        stringCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                switch (response.code()) {
//                    case 200:
//                        break;
//                    default:
//
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//    }

    public ApiRetrofit() {
        mClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        //获取baseUrl
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl("http://gank.io/")//获取baseUrl
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static ApiRetrofit getInstance() {
        if (sApiRetrofit == null) {
            synchronized (Object.class) {
                if (sApiRetrofit == null) {
                    sApiRetrofit = new ApiRetrofit();
                }
            }
        }
        return sApiRetrofit;
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
