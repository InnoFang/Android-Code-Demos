package com.example.innf.criminalintent.database;

/**
 * Author: Inno Fang
 * Time: 2016/7/9 10:55
 * Description: 实现数据库操作相关代码的组织和归类
 */

public class CrimeDbSchema {

    //CrimeTable内部类的用途是定义描述数据元素的Srting常量
    public static final class CrimeTable{
        public static final String NAME = "criems";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }
    }
}
