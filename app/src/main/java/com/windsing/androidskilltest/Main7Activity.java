package com.windsing.androidskilltest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket server
 */
public class Main7Activity extends AppCompatActivity {

    private TextView textView;
    private ServerSocket serverSocket;
    Handler updateConversationHandler;
    Thread serverThread = null;

    public static final int SERVERPORT = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        textView = (TextView) findViewById(R.id.textView7);
        updateConversationHandler = new Handler();
        this.serverThread = new Thread(new ServerThread());
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ServerThread implements Runnable {

        @Override
        public void run() {
            Socket socket = null;
            try {
                serverSocket = new ServerSocket(SERVERPORT);
                while (!Thread.currentThread().isInterrupted()) {
                    socket = serverSocket.accept();
                    CommunicationThread commThread = new CommunicationThread(socket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class CommunicationThread implements Runnable {
        private Socket clientSocket;
        private BufferedReader input;

        public CommunicationThread(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String read = input.readLine();
                    updateConversationHandler.post(new UpdateUIThread(read));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class UpdateUIThread implements Runnable {
        private String msg;

        public UpdateUIThread(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            textView.setText(textView.getText().toString() + "Client Says" + msg + "\n");
        }
    }
}
