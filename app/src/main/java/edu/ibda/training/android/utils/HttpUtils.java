package edu.ibda.training.android.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DaiGooR on 9/16/2017.
 */

public class HttpUtils {

    public static String httpGetCall(String urlStr) {
        try{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result = "";
            String s;
            while (( s = in.readLine()) != null) {
                result += s;
            }
            return result;
        }catch(Exception e){
            Log.e("gagagaga", "Error in http connection "+e.toString());
        }
        return null;
    }

}
