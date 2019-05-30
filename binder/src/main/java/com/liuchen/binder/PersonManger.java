package com.liuchen.binder;

import android.os.IInterface;

import java.util.List;

/**
 * Author: 刘晨
 * Date: 2019/5/22 17:51
 */
public interface PersonManger extends IInterface {
    void addPerson(Person mPerson);
    List<Person> getPersonList();
}
