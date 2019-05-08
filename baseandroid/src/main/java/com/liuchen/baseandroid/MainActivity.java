package com.liuchen.baseandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liuchen.baseandroid.fourconstitute.FourConstituteActivity;
import com.liuchen.baseandroid.multithreading.MultiThreadingActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button fourConstituteBtn, layoutBtn, multithreadingBtn, animationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fourConstituteBtn = findViewById(R.id.fourConstituteBtn);
        layoutBtn = findViewById(R.id.layoutBtn);
        multithreadingBtn = findViewById(R.id.multithreadingBtn);
        animationBtn = findViewById(R.id.animationBtn);

        fourConstituteBtn.setOnClickListener(this);
        layoutBtn.setOnClickListener(this);
        multithreadingBtn.setOnClickListener(this);
        animationBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fourConstituteBtn:
                startActivity(new Intent(MainActivity.this, FourConstituteActivity.class));
                break;
            case R.id.layoutBtn:
                break;
            case R.id.multithreadingBtn:
                startActivity(new Intent(MainActivity.this, MultiThreadingActivity.class));
                break;
            case R.id.animationBtn:
                break;
        }
    }
}
