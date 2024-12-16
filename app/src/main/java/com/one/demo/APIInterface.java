package com.one.demo;

import com.one.demo.model.MultipleResources;

import retrofit2.Call;
import retrofit2.http.GET;

interface APIInterface {

    @GET("api/dashBoard")
    Call<MultipleResources> doGetListResources();
}
