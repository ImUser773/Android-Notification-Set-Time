package com.iamdeveloper.notificationsettime;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

/**
 * Created by IamDeveloper on 10/24/2016.
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(MyService.this);
        notification.setSmallIcon(R.mipmap.ic_launcher);
        NotificationCompat.InboxStyle inbox = new NotificationCompat.InboxStyle();

        String[] message = {"คุณได้รับข้อความใหม่","กรุณากดเพื่อดูข้อความ"
                ,"กรุณากดเพื่อดูข้อความ","กรุณากดเพื่อดูข้อความ"
                ,"กรุณากดเพื่อดูข้อความ","กรุณากดเพื่อดูข้อความ"};
        inbox.setBigContentTitle("New Message");
        for(String i :message){
            inbox.addLine(i);
        }
        notification.setStyle(inbox);

        Intent i = new Intent(MyService.this,MainActivity.class);
        TaskStackBuilder builder = TaskStackBuilder.create(MyService.this);
        builder.addParentStack(MainActivity.class);
        builder.addNextIntent(i);
        PendingIntent pIntent = builder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        notification.setContentIntent(pIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,notification.build());
        return super.onStartCommand(intent, flags, startId);
    }
}
