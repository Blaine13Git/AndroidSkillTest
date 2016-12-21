package com.windsing.androidskilltest;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.TimerTask;
import java.util.concurrent.FutureTask;

/**
 * 验证Handler效果 和其他页面的入口
 */
public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

    }

    //启动进度条
    public void startProgress(View view){
        new Thread(new Task()).start();
    }

    //启动page02,click
    public void startPage02(View view){
        Intent intent = new Intent();
        intent.setClass(this,Main2Activity.class);
        startActivity(intent);
    }

    //进入ActionBar activity
    public void goStartActionBar(View view){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),Main5Activity.class);
        startActivity(intent);
    }

    //Socket
    public void goSocket(View view){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),Main7Activity.class);
        startActivity(intent);
    }

    //SQL
    public void goSQLite(View view){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),Main6Activity.class);
        startActivity(intent);
    }

    //SharedPreference
    public void goPreference(View view){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),Main8Activity.class);
        startActivity(intent);
    }
    //SharedPreference
    public void goFragment(View view){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),Main10Activity.class);
        startActivity(intent);
    }



    /**
     * 进度条任务的的执行
     */
    class Task implements Runnable{

        @Override
        public void run() {
            for(int i = 0;i<=20;i++){
                final int value = i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(value);

                    }
                });
            }
        }
    }
}
