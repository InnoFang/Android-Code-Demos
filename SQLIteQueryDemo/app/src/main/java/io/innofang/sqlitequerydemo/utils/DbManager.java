package io.innofang.sqlitequerydemo.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import io.innofang.sqlitequerydemo.Person;

/**
 * Author: Inno Fang
 * Time: 2017/1/14 22:08
 * Description:
 */

public class DbManager {
    private static MySQLiteHelper sHelper;

    public static MySQLiteHelper getInstance(Context context) {
        if (sHelper == null) {
            sHelper = new MySQLiteHelper(context);
        }
        return sHelper;
    }

    /**
     * 根据sql语句查询获得cursor对象
     *
     * @param db           数据库对象
     * @param sql          查询的sql对象
     * @param slectionArgs 查询条件的占位符
     * @return 查询结果
     */
    public static Cursor selectDataBySql(SQLiteDatabase db, String sql, String[] slectionArgs) {
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, slectionArgs);
        }
        return cursor;
    }

    /**
     * 将查询的cursor对象转换成list集合
     *
     * @param cursor 游标对象
     * @return 集合对象
     */
    public static List<Person> cursorToList(Cursor cursor) {
        List<Person> list = new ArrayList<>();
        // 如果返回true表示下一条记录存在，否则表示游标中数据读取完毕
        while(cursor.moveToNext()){
            // 根据参数中指定的字段名称获取字段下标
            int columnIndex = cursor.getColumnIndex(Constant._ID);
            // 根据参数中指定的字段下标 获取对应int类型的value
            int _id = cursor.getInt(columnIndex);

            String name  = cursor.getString(cursor.getColumnIndex(Constant.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(Constant.AGE));

            Person person = new Person(_id, name, age);
            list.add(person);
        }
        return list;
    }

}
