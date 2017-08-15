package com.example.admin.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try{
            Thread.sleep(2000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        switch (intent.getAction()){
            case "getRepo":
                Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data")+ Thread.currentThread());
                break;
            case "getProfile":
                Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data") + Thread.currentThread());
        }


//        Log.d(TAG, "onHandleIntent: " + Thread.currentThread());
    }
    @Override
    public void onDestroy() {

        super.onDestroy();
        Log.d(TAG,"onDestroy: ");
    }

}
