package com.liuchen.baseandroid.fourconstitute.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liuchen.baseandroid.R;

/**
 * 参考资料:
 * https://blog.csdn.net/carson_ho/article/details/76101093
 */
public class ContentProviderActivity extends AppCompatActivity {
    private static final String TAG = "ContentProviderActivity";
    // 获取ContentResolver
    ContentResolver resolver;
    private static final String BASE_URI = "content://com.liuchen.baseandroid.fourconstitute.contentprovider.MyContentProvider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        resolver = getContentResolver();
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

        //对user表进行操作
        // 设置URI
        Uri uri_user = Uri.parse(BASE_URI + "/user");

        // 插入表中数据
        ContentValues values = new ContentValues();
        //values.put("_id", 3);
        values.put("name", "刘晨");
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver.insert(uri_user, values);
        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor = resolver.query(uri_user, new String[]{"id", "name"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.d(TAG, "onCreate: " + cursor.getInt(0) + " " + cursor.getString(1));
            }
            cursor.close();
        }

        //对job表进行操作
        // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
        /*Uri uri_job = Uri.parse(BASE_URI + "/job");

        // 插入表中数据
        ContentValues values2 = new ContentValues();
        //values2.put("_id", 3);
        values2.put("job", "NBA Player");
        resolver.insert(uri_job, values2);

        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor2 = resolver.query(uri_job, new String[]{"id", "job"}, null, null, null);
        if (cursor2 != null) {
            while (cursor2.moveToNext()) {
                System.out.println("query job:" + cursor2.getInt(0) + " " + cursor2.getString(1));
            }
            cursor2.close();
        }*/
    }
}
