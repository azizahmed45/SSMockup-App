package com.mrgreenapps.screenshotmockup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mrgreenapps.screenshotmockup.api.ApiService;
import com.mrgreenapps.screenshotmockup.api.models.Mockup;
import com.mrgreenapps.screenshotmockup.ssmockup.SSMockup;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockupActivity extends AppCompatActivity {

    private static final String TAG = "MockupActivity";

    private Mockup mockup;
    private ImageView mockupImageView;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mockup);
        mockupImageView = findViewById(R.id.mockup_image);

        mockup = (Mockup) getIntent().getSerializableExtra("mockup");

        downloadMockupFile();

    }

    public void downloadMockupFile(){
        ApiService.getApiClient().downloadMockupFile(mockup.mockupFiles.get(0).id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                File file = new File(getCacheDir() + "/download.jpg");
                                OutputStream out = new FileOutputStream(file);
                                IOUtils.write(response.body().bytes(), out);
                                out.close();

                                Glide.with(MockupActivity.this)
                                        .load(file)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .skipMemoryCache(true)
                                        .into(mockupImageView);

                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}