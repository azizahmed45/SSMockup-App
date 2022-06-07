package com.mrgreenapps.screenshotmockup.api;

import com.mrgreenapps.screenshotmockup.api.models.ApiPaginateResponse;
import com.mrgreenapps.screenshotmockup.api.models.Mockup;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {
    @GET("getAllMockups")
    public Call<ApiPaginateResponse<List<Mockup>>> getMockups(@Query("page") int page);

    @GET("downloadMockupFile/{fileId}")
    public Call<ResponseBody> downloadMockupFile(@Path("fileId") int fileId);
}
