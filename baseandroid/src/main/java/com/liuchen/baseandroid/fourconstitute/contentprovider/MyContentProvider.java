package com.liuchen.baseandroid.fourconstitute.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author: 刘晨
 * Date: 2019/5/16 14:55
 */
public class MyContentProvider extends ContentProvider {
    private static final String TAG = "MyContentProvider";
    private static final String AUT_HORITY = "com.liuchen.baseandroid.fourconstitute.contentprovider.MyContentProvider";
    private DBHelper mDbHelper;
    private SQLiteDatabase db;


    private static final UriMatcher mMatcher;
    public static final int User_Code = 1;
    public static final int Job_Code = 2;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 初始化
        mMatcher.addURI(AUT_HORITY, "user", User_Code);
        mMatcher.addURI(AUT_HORITY, "job", Job_Code);
        // 若URI资源路径 = content://cn.scu.myprovider/user ，则返回注册码User_Code
        // 若URI资源路径 = content://cn.scu.myprovider/job ，则返回注册码Job_Code
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DBHelper(getContext());
        db = mDbHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String table = getTableName(uri);
        return db.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        //返回MIME类型对应内容的URI
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //插入一条数据。参数values是需要插入的值
        Log.d(TAG, "insert: " + uri.toString());
        String table = getTableName(uri);
        //插入数据 将返回的行数添加到uri
        ContentUris.withAppendedId(uri, db.insert(table, null, values));
        //如果设置了registerContentObserver需要进行刷新
        getContext().getContentResolver().notifyChange(uri, null);
        Log.d(TAG, "insert: " + uri.toString());
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        //根据条件删除数据
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        //根据条件更新数据
        return 0;
    }

    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     */
    private String getTableName(Uri uri) {
        String tableName = null;
        switch (mMatcher.match(uri)) {
            case User_Code:
                tableName = DBHelper.TABLE_NAME_USER;
                break;
            case Job_Code:
                tableName = DBHelper.TABLE_NAME_JOB;
                break;
        }
        return tableName;
    }
}
