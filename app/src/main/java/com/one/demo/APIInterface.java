package com.one.demo;

import com.one.demo.modelapi.MultipleResources;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("api/dashBoard")
    Call<MultipleResources> doGetListResources();
}
