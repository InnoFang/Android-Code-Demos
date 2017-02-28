package io.innofang.sqliteadapterdemo.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import io.innofang.sqliteadapterdemo.bean.Person;

/**
 * Author: Inno Fang
 * Time: 2017/1/16 12:00
 * Description:
 */

public class DbManager {
    private static MySQLiteHelper sHelper;

    public static MySQLiteHelper getInstance(Context context) {
        if (sHelper == null){
            sHelper = new MySQLiteHelper(context);
        }
        return sHelper;
    }

    public static Cursor selectDataBySql(SQLiteDatabase db, String sql, String[] selectionArgs){
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }

    public static List<Person> cursorToList(Cursor cursor){
        List<Person> list = new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseSchema.Table.Cols.ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Table.Cols.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(DatabaseSchema.Table.Cols.AGE));
            Person person = new Person(name, id, age);
            list.add(person);
        }
        return list;
    }
}
