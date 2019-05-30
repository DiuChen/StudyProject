package com.liuchen.popupwindow;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mainLin;
    private ImageView addIv;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLin = findViewById(R.id.mainLin);
        addIv = findViewById(R.id.addIv);

        addIv.setOnClickListener(this);
        initPopupWindow();


        mainLin.post(new Runnable() {
            @Override
            public void run() {
                /*Drawable dimDrawable = new ColorDrawable(Color.BLACK);
                dimDrawable.setBounds(0, 0, mainLin.getWidth(), mainLin.getHeight());
                dimDrawable.setAlpha((int) (255.0F * 0.4));
                mainLin.getOverlay().add(dimDrawable);*/

                /*View view = getWindow().getDecorView();
                Drawable dimDrawable1 = new ColorDrawable(Color.RED);
                dimDrawable1.setBounds(0, 0, view.getWidth(), view.getHeight());
                dimDrawable1.setAlpha((int) (255.0F));
                view.getOverlay().add(dimDrawable1);*/

                /*ViewGroup parent = (ViewGroup) getWindow().getDecorView().getRootView();
                Drawable dimDrawable2 = new ColorDrawable(Color.BLUE);
                dimDrawable2.setBounds(0, 0, parent.getWidth(), parent.getHeight());
                dimDrawable2.setAlpha((int) (255.0F * 0.4));
                ViewGroupOverlay overlay = parent.getOverlay();
                overlay.add(dimDrawable2);*/
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addIv:
                ViewGroup parent = (ViewGroup) getWindow().getDecorView().getRootView();
                Drawable dimDrawable = new ColorDrawable(Color.BLACK);
                dimDrawable.setBounds(0, 0, parent.getWidth(), parent.getHeight());
                dimDrawable.setAlpha((int) (255.0F * 0.4));
                ViewGroupOverlay overlay = parent.getOverlay();
                overlay.add(dimDrawable);
                popupWindow.showAsDropDown(addIv, 0, 0);
                break;
        }
    }

    private void initPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popup_list, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        TextView btn1 = view.findViewById(R.id.btn1);
        TextView btn2 = view.findViewById(R.id.btn2);
        TextView btn3 = view.findViewById(R.id.btn3);
        TextView btn4 = view.findViewById(R.id.btn4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "btn1", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "btn2", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "btn3", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "btn4", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ViewGroup parent = (ViewGroup) getWindow().getDecorView().getRootView();
                ViewGroupOverlay overlay = parent.getOverlay();
                overlay.clear();
            }
        });

        /*EasyPopup easyPopup = EasyPopup.create()
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.4f)*/
    }
}
