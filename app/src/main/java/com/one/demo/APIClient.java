package com.one.demo;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class APIClient {

    private static Retrofit retrofit = null;

    static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl("https://047a-2405-201-201f-9136-e12d-1ccc-a312-18ad.ngrok-free.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

}


//class APIClient {
//
//    private static Retrofit retrofit = null;
//
//    static Retrofit getClient() {
//
//
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("text/plain");
//        RequestBody body = RequestBody.create(mediaType, "");
//        Request request = new Request.Builder()
//                .url("https://047a-2405-201-201f-9136-e12d-1ccc-a312-18ad.ngrok-free.app/api/dashBoard")
//                .method("GET", body)
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl("https://047a-2405-201-201f-9136-e12d-1ccc-a312-18ad.ngrok-free.app")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//
//        return retrofit;
//    }
//
//}
