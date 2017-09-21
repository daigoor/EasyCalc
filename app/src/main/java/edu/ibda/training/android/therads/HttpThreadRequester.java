package edu.ibda.training.android.therads;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.ibda.training.android.utils.HttpUtils;

/**
 * Created by DaiGooR on 9/16/2017.
 */

public class HttpThreadRequester extends Thread {

    private String url;

    public HttpThreadRequester (String url) {
        this.url = url;
    }
    @Override
    public void run() {
        while (true) {
            try {
                String result = HttpUtils.httpGetCall(url);
                sleep(1000*10);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
