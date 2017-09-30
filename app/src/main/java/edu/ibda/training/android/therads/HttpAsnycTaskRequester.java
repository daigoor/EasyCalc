package edu.ibda.training.android.therads;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

public class HttpAsnycTaskRequester extends AsyncTask<String , Void, String>{

    private Context cntx;
    private String name;

    public HttpAsnycTaskRequester (Context cntx, String name) {
        this.cntx = cntx;
        this.name = name;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        return  HttpUtils.httpGetCall(url);
    }

    @Override
    protected void onPostExecute(String param) {
        super.onPostExecute(param);
        int responseLength = param.length();
        Intent intent = new Intent(name);
        intent.putExtra("responseLength", responseLength);
        this.cntx.sendBroadcast(intent);
    }
}
