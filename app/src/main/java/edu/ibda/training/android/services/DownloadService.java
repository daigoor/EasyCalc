package edu.ibda.training.android.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import edu.ibda.training.android.therads.HttpAsnycTaskRequester;

/**
 * Created by DaiGooR on 9/23/2017.
 */

public class DownloadService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpAsnycTaskRequester requester = new HttpAsnycTaskRequester(this, "easycalc.external.broadcast");
        requester.execute("http://www.google.com");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
