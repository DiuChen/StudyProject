package com.liuchen.baseandroid.fourconstitute.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.liuchen.baseandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 参考资料:https://www.jianshu.com/p/95ec2a23f300
 * 当服务通过startService启动时 必须通过stopService才能停止
 * 此时bindService后再unbindService不能停止服务
 * 当服务通过bindService启动时 所有组件都解绑时(unbindService)服务会停止
 * 但是如果通过bindService启动后执行了startService 所有组件都解绑后服务也不会停止
 * 只有执行stopService才能停止
 */
public class ServiceActivity extends AppCompatActivity {

    @BindView(R.id.startServer)
    Button startServer;
    @BindView(R.id.stopService)
    Button stopService;
    @BindView(R.id.bindService)
    Button bindService;
    @BindView(R.id.unbindService)
    Button unbindService;

    private static final String TAG = "MyServer";
    private Intent intent;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
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

    @OnClick({R.id.startServer, R.id.stopService, R.id.bindService, R.id.unbindService})
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
        }
    }
}
