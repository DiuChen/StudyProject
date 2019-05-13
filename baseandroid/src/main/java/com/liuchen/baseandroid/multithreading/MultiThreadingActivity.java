package com.liuchen.baseandroid.multithreading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liuchen.baseandroid.R;
import com.liuchen.baseandroid.multithreading.handler.HandlerActivity;
import com.liuchen.baseandroid.multithreading.thread.ThreadActivity;

public class MultiThreadingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button threadBtn, handlerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_threading);
        threadBtn = findViewById(R.id.threadBtn);
        handlerBtn = findViewById(R.id.handlerBtn);

        threadBtn.setOnClickListener(this);
        handlerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.threadBtn:
                startActivity(new Intent(MultiThreadingActivity.this, ThreadActivity.class));
                break;
            case R.id.handlerBtn:
                startActivity(new Intent(MultiThreadingActivity.this, HandlerActivity.class));
                break;
        }
    }
}
