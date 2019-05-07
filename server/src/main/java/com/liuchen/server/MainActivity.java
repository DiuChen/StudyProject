package com.liuchen.server;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.startServer)
    Button startServer;
    @BindView(R.id.stopService)
    Button stopService;
    @BindView(R.id.bindService)
    Button bindService;
    @BindView(R.id.unbindService)
    Button unbindService;
    @BindView(R.id.stopSelf)
    Button stopSelf;

    private static final String TAG = "MyServer";
    private Intent intent;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        intent = new Intent(this, MyServer.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected: ");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: ");
            }
        };
    }

    /**
     * @param view
     */
    @OnClick({R.id.startServer, R.id.stopService, R.id.bindService, R.id.unbindService, R.id.stopSelf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startServer:
                startService(intent);
                break;
            case R.id.stopService:
                stopService(intent);
                break;
            case R.id.bindService:
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                unbindService(serviceConnection);
                break;
            case R.id.stopSelf:

                break;
        }
    }
}
