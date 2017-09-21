package edu.ibda.training.android.activities.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import edu.ibda.training.android.R;
import edu.ibda.training.android.activities.AbstractActivity;
import edu.ibda.training.android.database.SharedPrefManager;
import edu.ibda.training.android.therads.HttpAsnycTaskRequester;

/**
 * Created by DaiGooR on 9/16/2017.
 */

public class LoadingActivity extends AbstractActivity {

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resLength = intent.getIntExtra("responseLength" , -1);
            dateLoaded();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter("easycalc.intent.broadcast");
        registerReceiver(receiver , filter);
    }

    @Override
    protected void init() {
//        HttpThreadRequester requester = new HttpThreadRequester("https://stackoverflow.com/questions/20321799/android-http-get");
//        requester.start();
        HttpAsnycTaskRequester requester = new HttpAsnycTaskRequester(this);
        requester.execute("http://www.google.com");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected int loadLayout() {
        return R.layout.loading_layout;
    }

    private void dateLoaded () {
        SharedPrefManager.getInstance(this).setLoaded();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
