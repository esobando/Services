package com.example.admin.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    private  static final String TAG = "MyServiceTAG";
    MyBoundService myBoundService;
    Boolean isBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       TextView textView = (TextView) findViewById(R.id.NumberReceive);

        Intent intent = getIntent();
        String s = intent.getStringExtra("KEY");
        textView.setText(s);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder =(MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            Log.d(TAG,"onServiceConnected: " + myBoundService.getRandomData());
            isBound= true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = false;
        }
    };



    public void startServices(View view)
    {
        Intent normalIntent = new Intent(this, MyNormalService.class);
        Intent intIntent = new Intent(this, MyIntentService.class);
        Intent boundIntent = new Intent(this, MyBoundService.class);


        switch (view.getId())
        {

            case R.id.btnBindServices:
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
//                intIntent.putExtra("data", "This is an intent service profile");
//                intIntent.setAction("getProfile");
//                startService(intIntent);

                break;


        }}
}
