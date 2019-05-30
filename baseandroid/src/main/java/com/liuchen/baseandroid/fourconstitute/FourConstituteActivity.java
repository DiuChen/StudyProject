package com.liuchen.baseandroid.fourconstitute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liuchen.baseandroid.fourconstitute.activity.ActivityActivity;
import com.liuchen.baseandroid.R;
import com.liuchen.baseandroid.fourconstitute.contentprovider.ContentProviderActivity;
import com.liuchen.baseandroid.fourconstitute.service.ServiceActivity;

public class FourConstituteActivity extends AppCompatActivity implements View.OnClickListener {
    private Button activityBtn, broadcastBtn, contentProviderBtn, serviceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_constitute);

        activityBtn = findViewById(R.id.activityBtn);
        broadcastBtn = findViewById(R.id.broadcastBtn);
        contentProviderBtn = findViewById(R.id.contentProviderBtn);
        serviceBtn = findViewById(R.id.serviceBtn);

        activityBtn.setOnClickListener(this);
        broadcastBtn.setOnClickListener(this);
        contentProviderBtn.setOnClickListener(this);
        serviceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activityBtn:
                startActivity(new Intent(FourConstituteActivity.this, ActivityActivity.class));
                break;
            case R.id.broadcastBtn:
                break;
            case R.id.contentProviderBtn:
                startActivity(new Intent(FourConstituteActivity.this, ContentProviderActivity.class));
                break;
            case R.id.serviceBtn:
                startActivity(new Intent(FourConstituteActivity.this, ServiceActivity.class));
                break;
        }
    }
}
