package com.example.admin.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private  static final String TAG = "MyServiceTAG";
        MyBoundService myBoundService;
    Boolean isBound = false;
    private Button buttonStart;
    private Button buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);

        //attaching onclicklistener to buttons
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

    }

    public void startServices(View view)
    {
        Intent normalIntent = new Intent(this, MyNormalService.class);
        Intent intIntent = new Intent(this, MyIntentService.class);
        Intent boundIntent = new Intent(this, MyBoundService.class);



        switch (view.getId())
        {
            case R.id.btnStartNormalServices:
                normalIntent.putExtra("data", "This is a normal service");
                startService(normalIntent);
                break;

            case R.id.btnStopNormalServices:
                normalIntent.putExtra("data", "This is stop normal service");
                stopService(normalIntent);
                break;

            case R.id.btnStartIntentServices:
                intIntent.putExtra("data", "This is an intent service");
                intIntent.setAction("getRepo");
                startService(intIntent);

                break;

            case R.id.btnBindServices:
                 bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
//                intIntent.putExtra("data", "This is an intent service profile");
//                intIntent.setAction("getProfile");
//                startService(intIntent);

                break;

            case R.id.btnUnBindServices:

                if(isBound){
                    unbindService(serviceConnection);
                }
                break;
            case R.id.btnrandom:
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("KEY", " " + NumberG);
                startActivity(intent);

                break;








        }}

            int NumberG;

        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(TAG, "onServiceConnected: ");
                MyBoundService.MyBinder myBinder =(MyBoundService.MyBinder) iBinder;
                myBoundService = myBinder.getService();
                NumberG = myBoundService.getRandomData();
                Log.d(TAG,"onServiceConnected: " + NumberG);
                isBound= true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(TAG, "onServiceDisconnected: ");
                isBound = false;
            }
        };

    public void onClick(View view)
    {
        if (view == buttonStart) {
            //start the service here
            startService(new Intent(this, MusicService.class));
        } else if (view == buttonStop) {
            stopService(new Intent(this, MusicService.class));
        }
    }
}

