package com.windsing.androidskilltest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.windsing.androidskilltest.tools.PositiveActivity;

/**
 * 自定义弹出窗口
 */
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void openAlert(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main3Activity.this);
//        alertDialogBuilder.setTitle(this.getTitle()+"……");
        alertDialogBuilder.setTitle("What do U want?");
        alertDialogBuilder.setMessage("Go home page?");

        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //go back home activity
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),PositiveActivity.class);
                startActivity(intent);
            }
        });

        // set negative button: No message
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // cancel the alert box and put a Toast to the user
                dialogInterface.cancel();
                Toast.makeText(getApplicationContext(),"U chose a negative answer",Toast.LENGTH_LONG).show();
            }
        });

        // set neutral button: Exit the app message
        alertDialogBuilder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Main3Activity.this.finish();
            }
        });

        //使用设置好的alertDialogBuilder创建AlterDialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
