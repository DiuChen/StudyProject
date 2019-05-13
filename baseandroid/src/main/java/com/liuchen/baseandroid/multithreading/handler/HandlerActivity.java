package com.liuchen.baseandroid.multithreading.handler;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liuchen.baseandroid.R;

/**
 * 参考资料:
 * https://blog.csdn.net/fnhfire_7030/article/details/79518819
 */
public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "HandlerActivity";
    private TextView numberTv;
    private Button threadLocalBtn;
    private int num;
    private Handler handler,myhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        numberTv = findViewById(R.id.numberTv);
        threadLocalBtn = findViewById(R.id.threadLocalBtn);

        threadLocalBtn.setOnClickListener(this);

        //handler的基本使用
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                num++;
                numberTv.setText(num + "");
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(1);
                }


            }
        }).start();

        //handler在子线程创建 其他线程可以通过myhandler切换到该线程执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 线程1启动--"+Thread.currentThread().getName());
                Looper.prepare();
                myhandler = new Handler();
                Looper.loop();
                Log.d(TAG, "run: loop()退出");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 线程2启动--"+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: ---------"+Thread.currentThread().getName());
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.threadLocalBtn:
                startActivity(new Intent(HandlerActivity.this, ThreadLocalActivity.class));
                break;
        }
    }
}
