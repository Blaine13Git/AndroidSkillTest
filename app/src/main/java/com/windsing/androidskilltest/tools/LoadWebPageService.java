package com.windsing.androidskilltest.tools;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadWebPageService extends Service {
    public LoadWebPageService() {
    }

    private int MSG_ID = 2;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Messenger messenger = (Messenger) extras.get("MESSENGER");
            String url = (String) extras.get("URL");
            CommunicationThread comm = new CommunicationThread(messenger, url);
            new Thread(comm).start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class CommunicationThread implements Runnable {

        private Messenger messenger;
        String url;

        public CommunicationThread(Messenger messenger, String url) {
            this.messenger = messenger;
            this.url = url;
        }

        @Override
        public void run() {
            String response = "";
            URL url_url = null;
            HttpURLConnection conn = null;
            try {
                url_url = new URL(url);
                conn = (HttpURLConnection) url_url.openConnection();
                conn.connect();

                InputStream content = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
                String s = "";
                while ((s = bufferedReader.readLine()) != null) {
                    response += s;
                }

                Message message = Message.obtain();
                message.arg1 = MSG_ID;
                message.obj = response + "Hello";
                messenger.send(message);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
