package com.one.demo;

import com.one.demo.modelapi.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
public interface LanguageApi {
    @GET("api/language")
    Call<Example> getLanguages(@Header("Authorization") String authHeader);
}
