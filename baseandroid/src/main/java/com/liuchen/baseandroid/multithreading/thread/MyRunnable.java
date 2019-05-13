package com.liuchen.baseandroid.multithreading.thread;

import android.util.Log;

/**
 * Author: 刘晨
 * Date: 2019/5/8 18:29
 */
public class MyRunnable implements Runnable {
    private static final String TAG = "MyRunnable";
    private int num = 10;

    @Override
    public void run() {
        Log.d(TAG, "run: 启动线程:" + Thread.currentThread().getName());

        while (true) {
            synchronized (this) {
                Log.d(TAG, "run: " + Thread.currentThread().getName() + " " + num);
                num--;
                if (num <= 0) break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
