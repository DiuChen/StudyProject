package com.liuchen.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private boolean isConnect = false;
    private static final String TAG = "MainActivity";
    private PersonManger personManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personManger == null) {
                    Log.e(TAG, "connect error");
                    return;
                }
                personManger.addPerson(new Person());
                Log.e(TAG, personManger.getPersonList().size() + "");
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        start();
                    }
                });
            }
        },2000);
    }

    private void start() {
        Intent intent = new Intent(this, ServerSevice.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "connect success");
            isConnect = true;
            personManger = BinderObj.asInterface(service);
            List<Person> personList = personManger.getPersonList();
            Log.e(TAG, personList.size() + "");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "connect failed");
            isConnect = false;
        }
    };
}
