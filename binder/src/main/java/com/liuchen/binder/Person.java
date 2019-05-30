package com.liuchen.binder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: 刘晨
 * Date: 2019/5/22 17:57
 */
public class Person implements Parcelable {
    private String name;
    private String tel;

    public Person() {
    }

    protected Person(Parcel in) {
        name = in.readString();
        tel = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(tel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
