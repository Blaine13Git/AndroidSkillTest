package com.windsing.androidskilltest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class Main9Activity extends AppCompatActivity {

    final Context context = this;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        webView = (WebView) findViewById(R.id.webView2);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.baidu.com/");
    }

    private class LoadWebViewAsync extends AsyncTask<String, Integer, String> {

        //构造函数AsyncTask<Params, Progress, Result>参数说明:
        //Params   启动任务执行的输入参数
        //Progress 后台任务执行的进度
        //Result   后台计算结果的类型

        //onPreExecute()方法用于在执行异步任务前,主线程做一些准备工作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("调用onPreExecute()方法--->准备开始执行异步任务");
        }

        //doInBackground()方法用于在执行异步任务,不可以更改主线程中UI
        @Override
        protected String doInBackground(String... params) {
            System.out.println("调用doInBackground()方法--->开始执行异步任务");
            return null;
        }

        //onPostExecute()方法用于异步任务执行完成后,在主线程中执行的操作
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "调用onPostExecute()方法--->异步任务执行完毕", 0).show();
        }

        //onProgressUpdate()方法用于更新异步执行中,在主线程中处理异步任务的执行信息
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        //onCancelled()方法用于异步任务被取消时,在主线程中执行相关的操作
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }

    //异步加载web view  asynchronous（async）
    public void loadWebView9(View view) {
        LoadWebViewAsync task = new LoadWebViewAsync();
        task.execute(new String[]{"http://www.javacodegeeks.com"});
    }

    public void dummyFunction9(View view) {
        Toast.makeText(this, "虚假的功能", Toast.LENGTH_LONG);
    }

}
