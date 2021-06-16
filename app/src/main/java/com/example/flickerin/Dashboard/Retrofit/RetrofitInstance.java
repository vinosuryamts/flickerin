package com.example.flickerin.Dashboard.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitInstance {

    //public static String BASE_URL = "https://api.github.com/";
    public static String BASE_URL;

    public static Retrofit retrofit;

    public static void setBaseUrl(String baseUrl){
        BASE_URL = baseUrl;
    }

    public static Retrofit getRetrofitClient(){

        if(retrofit == null){

            /*OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20,TimeUnit.SECONDS).build();*/

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60,TimeUnit.SECONDS).build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
