package edu.ibda.training.android.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import edu.ibda.training.android.beans.Raw;
import edu.ibda.training.android.utils.Constants;

/**
 * Created by DaiGooR on 9/9/2017.
 */

public class SharedPrefManager {

    private static final String TAG = "SharedPrefManager";

    private static SharedPrefManager instance;

    private SharedPreferences sharedPref;
    private Context context;

    private SharedPrefManager(Context context) {
        this.context = context;
        sharedPref = this.context.getSharedPreferences(Constants.SP_NAME, context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance (Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public void setHistory (Raw raw) throws JSONException {
        SharedPreferences.Editor editor = this.sharedPref.edit();

        try {
            JSONArray array = this.getHistory();
            array.put(new JSONObject(new Gson().toJson(raw)));
            editor.putString("history", array.toString());
            editor.commit();
        }catch (JSONException e) {
            JSONArray jsonArray = new JSONArray(Arrays.asList(e.getStackTrace()));
            Log.e(TAG, "There was an error: " + e.getMessage() + " ,stack: " + jsonArray.toString());
            throw e;
        }
    }

    public JSONArray getHistory () throws JSONException{
        JSONArray array = new JSONArray();
        try {
            String history = sharedPref.getString("history", null);
            if (history != null) {
                array = new JSONArray(history);
            }
        } catch (JSONException e) {
            JSONArray jsonArray = new JSONArray(Arrays.asList(e.getStackTrace()));
            Log.e(TAG, "There was an error: " + e.getMessage() + " ,stack: " + jsonArray.toString());
            throw e;
        }
        return array;
    }

    public boolean isLoaded() {
        return this.sharedPref.getBoolean("is_loadedx", false);
    }

    public void setLoaded () {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putBoolean("is_loadedx", true);
        editor.commit();
    }

    public void disableLoaded () {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putBoolean("is_loadedx", false);
        editor.commit();
    }
}
