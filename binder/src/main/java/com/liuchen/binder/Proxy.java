package com.liuchen.binder;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;

/**
 * Author: 刘晨
 * Date: 2019/5/22 18:00
 */
public class Proxy implements PersonManger {
    private IBinder mIBinder;
    public Proxy(IBinder mIBinder) {
        this.mIBinder =mIBinder;
    }

    @Override
    public void addPerson(Person mPerson) {
        Parcel data = Parcel.obtain();
        Parcel replay = Parcel.obtain();

        try {
            data.writeInterfaceToken(BinderObj.DESCRIPTOR);
            if (mPerson != null) {
                data.writeInt(1);
                mPerson.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            mIBinder.transact(BinderObj.TRANSAVTION_addPerson, data, replay, 0);
            replay.readException();
        } catch (RemoteException e){
            e.printStackTrace();
        } finally {
            replay.recycle();
            data.recycle();
        }
    }

    @Override
    public List<Person> getPersonList() {
        Parcel data = Parcel.obtain();
        Parcel replay = Parcel.obtain();
        List<Person> result = null;
        try {
            data.writeInterfaceToken(BinderObj.DESCRIPTOR);
            mIBinder.transact(BinderObj.TRANSAVTION_getPerson, data, replay, 0);
            replay.readException();
            result = replay.createTypedArrayList(Person.CREATOR);
        }catch (RemoteException e){
            e.printStackTrace();
        } finally{
            replay.recycle();
            data.recycle();
        }
        return result;
    }

    @Override
    public IBinder asBinder() {
        return null;
    }
}
