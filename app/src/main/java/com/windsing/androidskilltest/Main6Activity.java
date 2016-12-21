package com.windsing.androidskilltest;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.windsing.androidskilltest.tools.Student;
import com.windsing.androidskilltest.tools.StudentOperations;

import java.util.List;

/**
 * 使用ListView组件的activity 集成的 父类应该是 ListActivity
 * 使用setListActivity()方法
 *
 * 如果是继承activity的话，那么你用listview的时候id可以随便命名，但是继承listactivity的话，listview的id只能是命名为 @android:id/list
 * 还有一点就是一个控件的id是 @android :id/empty的只能在继承listactivity才能使用，该控件在listview没有数据的时候才会显示
 */
public class Main6Activity extends ListActivity {

    private StudentOperations studentDBOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        studentDBOperations = new StudentOperations(this);
        studentDBOperations.open();

        List values = studentDBOperations.getAllStudents();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

    }

    public void addUser(View view) {
        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();

        EditText editText = (EditText) findViewById(R.id.editText);
        Student student = studentDBOperations.addStudent(editText.getText().toString());
        adapter.add(student);
    }

    public void deleteFirstUser(View view) {
        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
        Student student = null;
        if (getListAdapter().getCount() > 0) {
            student = (Student) getListAdapter().getItem(0);
            studentDBOperations.deleteStudent(student);
            adapter.remove(student);
        }
    }

    @Override
    protected void onResume() {
        studentDBOperations.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        studentDBOperations.close();
        super.onPause();
    }
}
