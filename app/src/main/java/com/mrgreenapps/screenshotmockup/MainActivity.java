package com.mrgreenapps.screenshotmockup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.mrgreenapps.screenshotmockup.ssmockup.MockupConfig;
import com.mrgreenapps.screenshotmockup.ssmockup.SSMockup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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