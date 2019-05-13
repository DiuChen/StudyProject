package com.liuchen.baseandroid.multithreading.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liuchen.baseandroid.R;

/**
 * 参考资料:
 * Runnable与Thread的区别详解 https://blog.csdn.net/zhongwn/article/details/24319511
 */
public class ThreadActivity extends AppCompatActivity {
    private static final String TAG = "ThreadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

       /*在程序开发中只要是多线程肯定永远以实现Runnable接口为主，因为实现Runnable接口相比继承Thread类有如下好处：
        •避免点继承的局限，一个类可以继承多个接口。
        •适合于资源的共享*/
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable);
        final Thread thread2 = new Thread(myRunnable);
        thread1.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    thread2.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
