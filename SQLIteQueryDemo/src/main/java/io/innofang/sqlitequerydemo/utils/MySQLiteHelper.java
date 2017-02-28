package io.innofang.sqlitequerydemo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author: Inno Fang
 * Time: 2017/1/14 21:56
 * Description:
 */

public class MySQLiteHelper extends SQLiteOpenHelper{

    public MySQLiteHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MySQLiteHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null,
                Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + Constant.TABLE_NAME + "(" + Constant._ID
                + " Integer primary key, " + Constant.NAME
                + " varchar(10), " + Constant.AGE + " Integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
