package io.innofang.sqlitequerydemo;

/**
 * Author: Inno Fang
 * Time: 2017/1/15 23:54
 * Description:
 */

public class Person {
    private int _id;
    private String name;
    private int age;

    public Person(int _id, String name, int age) {
        this._id = _id;
        this.name = name;
        this.age = age;
    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "id = " + _id + "  name = " + name + "  age = " + age;
    }
}
