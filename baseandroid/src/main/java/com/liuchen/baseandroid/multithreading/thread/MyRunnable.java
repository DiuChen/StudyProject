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
        while (num > 0) {
            synchronized (this){
                num--;
                Log.d(TAG, "run: " + Thread.currentThread().getName() + " " + num);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
