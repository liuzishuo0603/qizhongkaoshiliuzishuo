package com.example.dell.myapplication.api;

import com.example.dell.myapplication.bean.RootBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiSerivce {
    String mUrl = "https://api.apiopen.top/";

    @GET("getJoke")
    Observable<RootBean> getData();
}
