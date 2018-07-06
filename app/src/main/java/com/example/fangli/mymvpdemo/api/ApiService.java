package com.example.fangli.mymvpdemo.api;

import com.example.fangli.mymvpdemo.model.LearnResponseModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chenyu.
 * Created on 上午11:56 2018/5/31.
 * Author'github https://github.com/PrettyAnt
 */

public interface ApiService {

    //    @FormUrlEncoded
//    @GET("api/data/Android/10/1")
//    Observable<LearnResponseModel> getVideoPath(@Query("num") String num,@Query("page") String page );

    @GET("api/data/Android/{num}/{page}")
    Observable<LearnResponseModel> getVideoPath(@Path("num") int num, @Path("page") int page );

//    @GET("topic")
//    Observable<ApiData> getHotTopic();

//    @GET("topic")
//    Observable<ApiData> getMoreHotTopic(@Query("lastCursor") String lastCursor,
//                                        @Query("pageSize") int pageSize);
//
    @GET("api/data/Android/{num}/{page}")
    Observable<Object> getHotTopicDetail(@Path("num") int num, @Path("page") int page );

//    @GET("/topic/instantview")
//    Observable<InstantReadData> getInstantRead(
//            @Query("topicId") String topicId);
}
