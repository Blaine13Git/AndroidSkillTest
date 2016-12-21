package com.windsing.androidskilltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 点击事件的两种调用方式，建议在.xml对应的Button view上设置
 * 这样使得代码更加简洁，易于维护
 */
public class Main2Activity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = (Button) findViewById(R.id.button3);
        startPage03();
    }

    //
    public void startPage03(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
    }

    //
    public void startPage04(View view){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),Main4Activity.class);
        startActivity(intent);
    }
}
