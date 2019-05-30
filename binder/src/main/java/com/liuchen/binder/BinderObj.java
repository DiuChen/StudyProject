package com.liuchen.binder;


import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Author: 刘晨
 * Date: 2019/5/22 17:52
 */
public abstract class BinderObj extends Binder implements PersonManger {
    public static final String DESCRIPTOR = "com.example.taolin.hellobinder";
    public static final int TRANSAVTION_getPerson = IBinder.FIRST_CALL_TRANSACTION;
    public static final int TRANSAVTION_addPerson = IBinder.FIRST_CALL_TRANSACTION + 1;

    public static PersonManger asInterface(IBinder mIBinder) {
        IInterface iInterface = mIBinder.queryLocalInterface(DESCRIPTOR);
        if (null != iInterface && iInterface instanceof PersonManger) {
            return (PersonManger) iInterface;
        }
        return new Proxy(mIBinder);
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;

            case TRANSAVTION_getPerson:
                data.enforceInterface(DESCRIPTOR);
                List<Person> result = this.getPersonList();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;

            case TRANSAVTION_addPerson:
                data.enforceInterface(DESCRIPTOR);
                Person arg0 = null;
                if (data.readInt() != 0) {
                    arg0 = Person.CREATOR.createFromParcel(data);
                }
                this.addPerson(arg0);
                reply.writeNoException();
                return true;
        }
        return super.onTransact(code, data, reply, flags);

    }

    @Override
    public IBinder asBinder() {
        return this;
    }

}
