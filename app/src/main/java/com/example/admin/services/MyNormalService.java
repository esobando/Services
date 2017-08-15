package com.example.admin.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyNormalService extends Service {
    private  static final String TAG = "MyServiceTAG";
    public MyNormalService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d(TAG,"onStartCommand: " + intent.getStringExtra("data"));

        stopSelf();

        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

}
