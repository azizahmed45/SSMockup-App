package com.mrgreenapps.screenshotmockup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mrgreenapps.screenshotmockup.api.models.MockupConfig;
import com.mrgreenapps.screenshotmockup.ssmockup.SSMockup;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SSMockup ssmockup = new SSMockup(
                this,
                new File(getCacheDir() + "/mockup.png"),
                new File(getCacheDir() + "/screenshot.png"),
                new MockupConfig(633, 169, 859, 681)
        );

        ssmockup.create(new File(getCacheDir() + "/output.jpg"));

    }
}