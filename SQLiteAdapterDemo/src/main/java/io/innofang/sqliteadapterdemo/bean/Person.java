package io.innofang.sqliteadapterdemo.bean;

/**
 * Author: Inno Fang
 * Time: 2017/1/16 12:03
 * Description:
 */

public class Person {
    private String mName;
    private int mId;
    private int mAge;

    public Person(String name, int id, int age) {
        mName = name;
        mId = id;
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public int getId() {
        return mId;
    }

    public int getAge() {
        return mAge;
    }

    @Override
    public String toString() {
        return "id : " + mId + "  name : " + mName +"  age : " + mAge;
    }
}
