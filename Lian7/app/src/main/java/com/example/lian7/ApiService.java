package com.example.lian7;

import bean.Alldata;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String FU ="http://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<Alldata> getData();
}
