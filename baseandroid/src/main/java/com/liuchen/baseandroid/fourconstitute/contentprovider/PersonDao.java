package com.liuchen.baseandroid.fourconstitute.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Author: 刘晨
 * Date: 2019/5/16 15:18
 */
public class PersonDao {
    private DBHelper helper;

    public PersonDao(Context context) {
        helper = new DBHelper(context);
    }

    //方法：插入操作，返回的long类型为：插入当前行的行号
    public long insertPerson(ContentValues values) {
        long id = -1;
        try (SQLiteDatabase database = helper.getWritableDatabase()) {
            id = database.insert("person", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public int deletePerson(String whereClause, String[] whereArgs) {
        int count = -1;
        try (SQLiteDatabase database = helper.getWritableDatabase()) {
            count = database.delete("person", whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int updatePerson(ContentValues values, String whereClause,
                            String[] whereArgs) {
        int count = -1;
        try (SQLiteDatabase database = helper.getWritableDatabase()) {
            count = database.update("person", values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public Cursor queryPersons(String selection, String[] selectionArgs) {
        Cursor cursor = null;
        try (SQLiteDatabase database = helper.getReadableDatabase()) {
            cursor = database.query(true, "person", null, selection,
                    selectionArgs, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }
}
