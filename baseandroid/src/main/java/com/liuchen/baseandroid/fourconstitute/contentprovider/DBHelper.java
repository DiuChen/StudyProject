package com.liuchen.baseandroid.fourconstitute.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * Author: 刘晨
 * Date: 2019/5/16 15:19
 */
public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "mydb.db"; // 数据库的名字
    private static int DB_VERSION = 1; // 数据库的版本

    // 表名
    public static final String TABLE_NAME_USER = "user";
    public static final String TABLE_NAME_JOB = "job";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建两个表格:用户表 和职业表
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USER + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_JOB + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + " job TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
