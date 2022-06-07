package com.mrgreenapps.screenshotmockup.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrgreenapps.screenshotmockup.api.ApiClient;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    public static ApiClient getApiClient() {
//        final String BASE_URL = "https://ssmockup.mrgreenapps.com/api/";
        final String BASE_URL = "http://9202-8-25-96-59.ngrok.io/api/";

        Retrofit retrofit;
        ApiClient apiClient;
        HttpLoggingInterceptor loggingInterceptor;
        OkHttpClient okHttpClient;

        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                //debug
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();


        Gson gson = new GsonBuilder()
                .setDateFormat("yyy-MM-dd HH:mm:ss")
                //For (fix) null value skip
                .serializeNulls()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        apiClient = retrofit.create(ApiClient.class);

        return apiClient;
    }

}
