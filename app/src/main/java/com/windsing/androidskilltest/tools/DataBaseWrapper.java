package com.windsing.androidskilltest.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 2016/12/16.
 */
public class DataBaseWrapper extends SQLiteOpenHelper {

    public static final String STUDENTS = "Students";
    public static final String STUDENT_ID = "_id";
    public static final String STUDENT_NAME = "_name";

    public static final String DATABASE_NAME = "Students.db";
    public static final int DATABASE_VERSION = 1;

    //creation SQLite
    private static final String DATABASE_CREATE = "create table " +
            STUDENTS + "(" +
            STUDENT_ID + " integer primary key autoincrement," +
            STUDENT_NAME + " text not null );";

    public DataBaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists" + STUDENTS);
        onCreate(sqLiteDatabase);
    }
}
