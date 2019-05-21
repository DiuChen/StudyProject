package com.liuchen.baseandroid.fourconstitute.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.util.Log;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: 刘晨
 * Date: 2019/5/17 11:37
 */
public class MyContentProviderTest {
    private static final String TAG = "MyContentProviderTest";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insert() {
        ContentResolver contentResolver = InstrumentationRegistry.getTargetContext().getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "刘晨1");
        values.put("address", "江西瑞金");
        Uri uri = Uri.parse("content://com.liuchen.baseandroid.fourconstitute.contentprovider.MyContentProvider");
        Uri myuri = contentResolver.insert(uri, values);
        Log.d(TAG, "insert: " + myuri.toString());
    }
}