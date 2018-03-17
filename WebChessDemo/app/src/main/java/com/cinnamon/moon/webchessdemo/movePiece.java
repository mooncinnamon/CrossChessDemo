package com.cinnamon.moon.webchessdemo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by moonp on 2018-03-13.
 */

public class MovePiece extends AsyncTask<String, String, String> {

    String ip = "192.168.255.6";
    int port = 15555;
    TextView text;

    MovePiece(TextView text) {
        this.text = text;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... strings) {
        String msg = null;
        try {
            Socket socket = new Socket(ip, port);
            Log.d("attach", "server");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            writer.println("send data");
            writer.flush();

            msg = reader.readLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return msg;
    }

    @Override
    protected void onPostExecute(String string){
        super.onPostExecute(string);
        text.setText(string);
    }
}
