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

//Alarm reciever class to create what happens when a notification is passed in.
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) { //When a notification is recieved.

        //Build a notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,"1")
                .setSmallIcon(R.drawable.icon_youdo)
                .setContentTitle("You:Do")
                .setContentText(intent.getStringExtra("Name"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        //Get the id of the recieved alarm
        int id = intent.getIntExtra("Id",0);
        Intent notificationIntent = new Intent (context,MainActivity.class); //Set up notification to take user to homepage of app.
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(context,id,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, mBuilder.build());
    }

}
