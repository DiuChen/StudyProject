package com.liuchen.popupwindow;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startTimer();
    }

    private void startTimer() {
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }

    //屏蔽返回键
    @Override
    public void onBackPressed() {
    }
}
