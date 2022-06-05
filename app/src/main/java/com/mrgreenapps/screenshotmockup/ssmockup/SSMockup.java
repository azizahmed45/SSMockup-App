package com.mrgreenapps.screenshotmockup.ssmockup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SSMockup {
    private Context context;
    private File mockupFile;
    private File screenshotFile;
    private MockupConfig mockupConfig;

    public SSMockup(Context context, File mockupFile, File screenshotFile, MockupConfig mockupConfig) {
        this.context = context;
        this.mockupFile = mockupFile;
        this.screenshotFile = screenshotFile;
        this.mockupConfig = mockupConfig;
    }

    public void create(File outputFile) {
        //mockup as main frame
        Bitmap mockupBitmap = bitmapFromFile(mockupFile);
        Bitmap screenshotBitmap = bitmapFromFile(screenshotFile);

        Bitmap mainBitmap = Bitmap.createBitmap(mockupBitmap.getWidth(), mockupBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mainBitmap);

        //draw screenshot
        canvas.drawBitmap(
                screenshotBitmap,
                null,
                new Rect(mockupConfig.left, mockupConfig.top, mockupConfig.right, mockupConfig.bottom),
                null
        );

        //draw mockup
        canvas.drawBitmap(mockupBitmap, 0, 0, null);




        //save bitmap to file
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            mainBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //bitmap from file
    private Bitmap bitmapFromFile(File file) {
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }
}
