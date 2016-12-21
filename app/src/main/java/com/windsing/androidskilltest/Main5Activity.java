package com.windsing.androidskilltest;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.windsing.androidskilltest.tools.LoadWebPageService;

public class Main5Activity extends AppCompatActivity {

    private static int MSG_ID = 2;
    private Handler handler;
    private WebView webView;
    private String content;

    public Main5Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.arg1 == MSG_ID){
                    content = (String) msg.obj;
                    this.post(new Runnable() {
                        @Override
                        public void run() {
                            webView.loadData(content,"text/html; charset=UTF-8",null);
                        }
                    });
                }
            }
        };
    }

    //点击事件
    public void launchBrowser(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dict.youdao.com/"));
        startActivity(intent);
    }

    public void loadWebView(View view){
        Intent intent = new Intent(this,LoadWebPageService.class);
        Messenger messenger = new Messenger(handler);
        intent.putExtra("MESSENGER",messenger);
        intent.putExtra("URL","http://dict.youdao.com/");
        startService(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main5,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                homeActivity();
                return true;
            case R.id.java:
                javaActivity();
                return true;
            case R.id.android:
                androidActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void androidActivity() {
        Toast.makeText(this,"this is android activity",Toast.LENGTH_LONG).show();
    }

    private void javaActivity() {
        Toast.makeText(this,"this is java activity",Toast.LENGTH_LONG).show();
    }

    private void homeActivity() {
        Toast.makeText(this,"this is home activity",Toast.LENGTH_LONG).show();
    }
}
