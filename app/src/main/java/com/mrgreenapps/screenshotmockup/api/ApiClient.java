package com.mrgreenapps.screenshotmockup.api;

import com.mrgreenapps.screenshotmockup.api.models.Mockup;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {
    @GET("mockups")
    public Call<Mockup> getMockups();
}
