package io.innofang.sqliteadapterdemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author: Inno Fang
 * Time: 2017/1/16 11:29
 * Description:
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    public MySQLiteHelper(Context context) {
        super(context,
                DatabaseSchema.DATABASE_NAME,
                null,
                DatabaseSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + DatabaseSchema.Table.TABLE_NAME + "("
                + DatabaseSchema.Table.Cols.ID + " Integer primary key, "
                + DatabaseSchema.Table.Cols.NAME + " varchar(10), "
                + DatabaseSchema.Table.Cols.AGE + " Integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
