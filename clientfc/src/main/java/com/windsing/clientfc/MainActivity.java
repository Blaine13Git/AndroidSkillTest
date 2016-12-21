package com.windsing.clientfc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private Socket socket;
    public static final int SERVER_PORT = 5000;
    private static final String SERVER_IP = "";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new ClientThread()).start();
    }

    public void sendMSG(View view){
        textView = (TextView) findViewById(R.id.textView);
        String msg = textView.getText().toString();
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            pw.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ClientThread implements Runnable{

        @Override
        public void run() {
            try {
                InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddress,SERVER_PORT);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
