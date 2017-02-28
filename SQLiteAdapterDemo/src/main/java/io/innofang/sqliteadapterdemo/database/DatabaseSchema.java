package io.innofang.sqliteadapterdemo.database;

/**
 * Author: Inno Fang
 * Time: 2017/1/16 11:33
 * Description:
 */

public class DatabaseSchema {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "info.db";

    public static final class Table{
        public static final String TABLE_NAME = "person";

        public static final class Cols{
            public static final String ID = "id";
            public static final String AGE = "age";
            public static final String NAME = "name";
        }
    }
}
