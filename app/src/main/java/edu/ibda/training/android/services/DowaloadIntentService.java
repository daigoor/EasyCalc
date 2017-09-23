package edu.ibda.training.android.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by DaiGooR on 9/23/2017.
 */

public class DowaloadIntentService extends IntentService {

    private int result = Activity.RESULT_CANCELED;

    public DowaloadIntentService() {
        super("intent_service_name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("IntentService", "service started");
        String name = intent.getStringExtra("name");
        String urlPath = intent.getStringExtra("url");
        String fileName = intent.getStringExtra("file_name");
        downloadFile(urlPath , fileName, name);
    }

    private void downloadFile (String urlPath, String fileName, String name) {

        File output = new File(Environment.getExternalStorageDirectory(),
                fileName);

        try {

            if (output.exists()) {
                output.delete();
            } else {
                output.createNewFile();
            }

            InputStream stream = null;
            FileOutputStream fos = null;

            URL url = new URL(urlPath);
            stream = url.openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            fos = new FileOutputStream(output.getPath());
            int next = -1;
            while ((next = reader.read()) != -1) {
                fos.write(next);
            }
            // successfully finished
            result = Activity.RESULT_OK;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        publishResults(output.getAbsolutePath(), name, result);
    }

    private void publishResults(String outputPath,String name, int result) {
        Intent intent = new Intent(name);
        int responseLength = outputPath.length();
        intent.putExtra("responseLength", responseLength);
        sendBroadcast(intent);
    }
}
