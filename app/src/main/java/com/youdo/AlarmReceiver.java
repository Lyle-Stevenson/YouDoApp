package com.youdo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.youdo.Calendar.AddEventActivity;
import com.youdo.Calendar.Event;

/**
 * Created by 40295568 on 06/03/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,"1")
                .setSmallIcon(R.drawable.flights)
                .setContentTitle(intent.getStringExtra("Name"))
                .setContentText(intent.getStringExtra("Desc"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        int id = intent.getIntExtra("Id",0);
        Intent notificationIntent = new Intent (context,MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(context,id,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, mBuilder.build());
        Toast.makeText(context, "Alarm recieved: " + intent.getStringExtra("Name"), Toast.LENGTH_SHORT).show();
    }

}
