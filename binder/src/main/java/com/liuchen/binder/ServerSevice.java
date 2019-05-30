package com.liuchen.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 刘晨
 * Date: 2019/5/22 18:06
 */
public class ServerSevice extends Service {
    private static final String TAG = "ServerSevice";
    private List<Person> mPeople = new ArrayList<>();


    @Override
    public void onCreate() {
        mPeople.add(new Person());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
    private BinderObj mStub = new BinderObj() {
        @Override
        public void addPerson(Person mPerson) {
            if (mPerson==null){
                mPerson = new Person();
                Log.e(TAG,"null obj");
            }
            mPeople.add(mPerson);
            Log.e(TAG,mPeople.size()+"");
        }

        @Override
        public List<Person> getPersonList() {
            return mPeople;
        }
    };
}
