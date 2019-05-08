package com.liuchen.baseandroid.fourconstitute.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liuchen.baseandroid.R;

public class SkipActivity extends AppCompatActivity {

    private static final String TAG = "Skip";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 第二个activity");
        setContentView(R.layout.activity_skip);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: 第二个activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: 第二个activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: 第二个activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: 第二个activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 第二个activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: 第二个activity");
    }
}
