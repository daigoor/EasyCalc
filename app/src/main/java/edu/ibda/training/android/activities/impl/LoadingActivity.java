package edu.ibda.training.android.activities.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;

import edu.ibda.training.android.R;
import edu.ibda.training.android.activities.AbstractActivity;
import edu.ibda.training.android.database.SharedPrefManager;
import edu.ibda.training.android.services.DowaloadIntentService;
import edu.ibda.training.android.services.DownloadService;
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
        IntentFilter filter = new IntentFilter("easycalc.internal.broadcast");
        registerReceiver(receiver , filter);
    }

    @Override
    protected void init() {
//        HttpThreadRequester requester = new HttpThreadRequester("https://stackoverflow.com/questions/20321799/android-http-get");
//        requester.start();
        HttpAsnycTaskRequester requester = new HttpAsnycTaskRequester(this, "easycalc.internal.broadcast");
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
//        SharedPrefManager.getInstance(this).setLoaded();
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);

        Intent intent = new Intent(getApplicationContext(), DownloadService.class);
        startService(intent);
//        runIntentService();
//        sendSMS();
        this.finish();
    }

    private void runIntentService () {
        Intent intent = new Intent(getApplicationContext(), DowaloadIntentService.class);
        intent.putExtra("url", "http://patentimages.storage.googleapis.com/US20050244132A1/US20050244132A1-20051103-D00004.png");
        intent.putExtra("file_name", "downloaded_img.png");
        intent.putExtra("name", "easycalc.external.broadcast");
        startService(intent);
    }


    private void sendSMS()
    {
        String number = "0598917280";  // The number on which you want to send SMS
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null));
        startActivity(intent);
    }
}
