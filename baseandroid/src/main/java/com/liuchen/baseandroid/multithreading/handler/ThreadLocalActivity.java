package com.liuchen.baseandroid.multithreading.handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liuchen.baseandroid.R;

/**
 * 研究ThreadLocal的用法
 * 当使用ThreadLocal 维护变量时，ThreadLocal 为每个使用该变量的线程提供独立的变量副本，
 * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 */
public class ThreadLocalActivity extends AppCompatActivity {
    private static final String TAG = "ThreadLocalActivity";
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_local);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "thread1: " + threadLocal.get());
                threadLocal.set("线程1");
                Log.d(TAG, "thread1: " + threadLocal.get());
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "thread2: " + threadLocal.get());
                threadLocal.set("线程2");
                Log.d(TAG, "thread2: " + threadLocal.get());
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "thread3: " + threadLocal.get());
                threadLocal.set("线程3");
                Log.d(TAG, "thread3: " + threadLocal.get());
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
