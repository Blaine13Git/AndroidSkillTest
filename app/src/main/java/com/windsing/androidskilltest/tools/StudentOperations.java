package com.windsing.androidskilltest.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/12/16.
 */
public class StudentOperations {

    private DataBaseWrapper dbHelper;
    private SQLiteDatabase database;
    private String[] STUDENT_TABLE_COLUMNS = {DataBaseWrapper.STUDENT_ID,DataBaseWrapper.STUDENT_NAME};

    public StudentOperations(Context context){
        dbHelper = new DataBaseWrapper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Student addStudent(String name){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseWrapper.STUDENT_NAME,name);
        long studId = database.insert(DataBaseWrapper.STUDENTS,null,cv);

        //使用id创建查询游标
        Cursor cursor = database.query(DataBaseWrapper.STUDENTS,
                STUDENT_TABLE_COLUMNS,
                DataBaseWrapper.STUDENT_ID + " = " + studId,
                null, null, null, null);

        cursor.moveToFirst();

        //根据游标解析学生信息
        Student newComment = parseStudent(cursor);

        return newComment;
    }

    private Student parseStudent(Cursor cursor) {
        Student student = new Student();
        student.setId(cursor.getInt(0));
        student.setName(cursor.getString(1));
        return student;
    }

    public List getAllStudents(){
        List students = new ArrayList();
        Cursor cursor = database.query(DataBaseWrapper.STUDENTS,STUDENT_TABLE_COLUMNS,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Student student = new Student();
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }

    public void deleteStudent(Student comment){
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DataBaseWrapper.STUDENTS,DataBaseWrapper.STUDENT_ID + " = " + id ,null);
    }

}
