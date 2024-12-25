package com.one.demo;

import com.one.demo.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface APIInterface {

    @GET("/api/language")
    Call<Example> getdata(@Header("Authorization") String authHeader);
}
