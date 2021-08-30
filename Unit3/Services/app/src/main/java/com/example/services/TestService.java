package com.example.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

public class TestService extends Service {

    public class TestBinder extends Binder {
        public TestService getService() {
            return TestService.this;
        }
    }

    TestBinder testBinder = new TestBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent newIntent = new Intent(TestService.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(TestService.this, 0, newIntent, 0);
        Notification notification = new Notification.Builder(TestService.this, "servicechannel")
                .setSmallIcon(R.drawable.ic_baseline_agriculture_24)
                .setContentText("Finished!")
                .setContentText("I am finished doing the work")
                .setContentIntent(pendingIntent)
                .build();

        NotificationManagerCompat manager = NotificationManagerCompat.from(TestService.this);
        manager.notify(1, notification);

        startForeground(1, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    public void doWork() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("I am being printed from the service");
//                stopForeground(true);

//                stopSelf();
            }
        };

        handler.postDelayed(runnable, 10000);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return testBinder;
    }
}
