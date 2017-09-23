package edu.ibda.training.android.broadcast;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import edu.ibda.training.android.R;
import edu.ibda.training.android.activities.impl.MainActivity;

/**
 * Created by DaiGooR on 9/23/2017.
 */

public class ExternalBroadcastReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ExternalBroadCast", "get something");

        Intent mainIntent = new Intent(context, MainActivity.class);
        PendingIntent ppIntent = PendingIntent.getActivity(context, -1, mainIntent, 0);

        Notification notification = new Notification.Builder(context).
                setContentIntent(ppIntent).
                setContentText("small content").
                setContentTitle("title").
                setAutoCancel(false).
                setStyle(new Notification.BigTextStyle()
                .bigText("content content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content cot content content content content content content content content content content content content content content content content content content content content " +
                        "content content content content content content content content content content content content content content content content content content ")).
                setSmallIcon(R.drawable.ic_home_white_24dp).
                build();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(100, notification);
    }

}
