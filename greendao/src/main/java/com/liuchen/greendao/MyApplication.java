package com.liuchen.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.liuchen.greendao.entity.DaoMaster;
import com.liuchen.greendao.entity.DaoSession;

/**
 * Author: 刘晨
 * Date: 2019/4/8 10:55
 */
public class MyApplication extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "aserbao.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
