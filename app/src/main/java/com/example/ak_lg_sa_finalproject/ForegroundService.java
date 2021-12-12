package com.example.ak_lg_sa_finalproject;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

/**
 * This class is used to implement the foreground service with notification
 * @author Anzhelika Kostyuk
 * @version 1.0
 */

public class ForegroundService extends Service {

    /**
     * starts Service
     * @param intent
     * @param flags
     * @param startId
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true)
                        {
                            Log.e("Foreground Service", "Foreground service is running!!!");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();

        //Id for service
        final String Channel_Id = "Foreground Service Id";

        //Notification channel to build the notification
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(Channel_Id, Channel_Id, NotificationManager.IMPORTANCE_DEFAULT);

            getSystemService(NotificationManager.class).createNotificationChannel(channel);

            //Design of the Notification
            Intent intent1 = new Intent(getApplicationContext(), ContactsActivity.class);
            Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent1, 0);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(getApplicationContext(), 0, intent2, 0);
            Notification.Builder builder = new Notification.Builder(this, Channel_Id)
                    .setSmallIcon(R.drawable.ic_launcher_custom_foreground)
                    .setContentTitle("Welcome to our Application!")
                    .setContentText("Do not forget to add more contacts to your list!!!")
                    .addAction(0, "ADD", pendingIntent)
                    .addAction(0, "IGNORE", pendingIntent2);

            startForeground(1, builder.build());
        }
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * communication with the client
     * @param intent
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

}
