package com.liuchen.contentprovidertest;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.insertBtn)
    Button insertBtn;

    private static final String TAG = "MainActivity";
    private static final String BASE_URI = "content://com.liuchen.baseandroid.fourconstitute.contentprovider.MyContentProvider";
    ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        resolver = getContentResolver();
    }

    private void insertAndSelect() {
        resolver.registerContentObserver(Uri.parse(BASE_URI), true, new ContentObserver(new Handler()) {
            @Override
            public boolean deliverSelfNotifications() {
                Log.d(TAG, "deliverSelfNotifications: ");
                return super.deliverSelfNotifications();
            }

            @Override
            public void onChange(boolean selfChange) {
                Log.d(TAG, "onChange1: ");
                super.onChange(selfChange);
            }

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                Log.d(TAG, "onChange2: ");
                super.onChange(selfChange, uri);
            }
        });

        Uri uri_user = Uri.parse(BASE_URI + "/user");
        ContentValues values = new ContentValues();
        values.put("name", "刘晨");
        resolver.insert(uri_user, values);
        Cursor cursor = resolver.query(uri_user, new String[]{"id", "name"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.d(TAG, "onCreate: " + cursor.getInt(0) + " " + cursor.getString(1));
            }
            cursor.close();
        }
    }

    @OnClick(R.id.insertBtn)
    public void onViewClicked() {
        insertAndSelect();
    }
}
