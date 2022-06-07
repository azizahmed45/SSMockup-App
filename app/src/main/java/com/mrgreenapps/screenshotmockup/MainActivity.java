package com.mrgreenapps.screenshotmockup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.mrgreenapps.screenshotmockup.api.models.ApiPaginateResponse;
import com.mrgreenapps.screenshotmockup.api.ApiService;
import com.mrgreenapps.screenshotmockup.api.models.Mockup;
import com.mrgreenapps.screenshotmockup.utils.PaginationScrollListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    RecyclerView mockupListView;
    MockupListAdapter mockupListAdapter;

    int currentPage = 1;
    int totalPages = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mockupListView = findViewById(R.id.mockup_list);
        mockupListAdapter = new MockupListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mockupListView.setLayoutManager(linearLayoutManager);
        mockupListView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                loadMockups();
            }

            @Override
            public int getTotalPageCount() {
                return totalPages;
            }

            @Override
            public boolean isLastPage() {
                return currentPage >= totalPages;
            }

            @Override
            public boolean isLoading() {
                return mockupListAdapter.isLoading();
            }
        });
        mockupListView.setAdapter(mockupListAdapter);

        mockupListAdapter.setOnMockupClickListener(new MockupListAdapter.OnMockupClickListener() {
            @Override
            public void onMockupClick(Mockup mockup) {
                Intent intent = new Intent(MainActivity.this, MockupActivity.class);
                intent.putExtra("mockup", mockup);
                startActivity(intent);
            }
        });

        loadMockups();




//        SSMockup ssmockup = new SSMockup(
//                this,
//                new File(getCacheDir() + "/mockup.png"),
//                new File(getCacheDir() + "/screenshot.png"),
//                new MockupConfig(633, 169, 859, 681)
//        );
//
//        ssmockup.create(new File(getCacheDir() + "/output.jpg"));

    }

    public void loadMockups(){
        mockupListAdapter.setLoading(true);
        ApiService.getApiClient().getMockups(currentPage).enqueue(new Callback<ApiPaginateResponse<List<Mockup>>>() {
            @Override
            public void onResponse(Call<ApiPaginateResponse<List<Mockup>>> call, Response<ApiPaginateResponse<List<Mockup>>> response) {
                mockupListAdapter.setLoading(false);
                if (response.isSuccessful()) {
                    List<Mockup> mockups = response.body().data;
                    mockupListAdapter.addMockupList(mockups);
                    currentPage++;
                    totalPages = response.body().to;
                }
            }

            @Override
            public void onFailure(Call<ApiPaginateResponse<List<Mockup>>> call, Throwable t) {
                mockupListAdapter.setLoading(false);
            }
        });
    }
}