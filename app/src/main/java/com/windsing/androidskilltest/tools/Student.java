package com.windsing.androidskilltest.tools;

/**
 * Created by user on 2016/12/16.
 */
public class Student {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
