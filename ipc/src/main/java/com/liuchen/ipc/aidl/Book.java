package com.liuchen.ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: 刘晨
 * Date: 2019/5/29 16:05
 */
public class Book implements Parcelable {
    private int id;
    private String name;
    private double price;

    protected Book(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readDouble();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
