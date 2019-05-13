package com.liuchen.statusbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button showStatusBarBtn;
    private Button hideStatusBarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showStatusBarBtn = findViewById(R.id.showStatusBarBtn);
        hideStatusBarBtn = findViewById(R.id.hideStatusBarBtn);

        showStatusBarBtn.setOnClickListener(this);
        hideStatusBarBtn.setOnClickListener(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showStatusBarBtn:
                ImmersionBar.with(this)
                        .hideBar(BarHide.FLAG_SHOW_BAR)
                        .init();
                break;
            case R.id.hideStatusBarBtn:
                ImmersionBar.with(this)
                        .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
                        .init();
                break;
        }
    }
}
