package com.cinnamon.moon.webchessdemo;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by moonp on 2018-03-13.
 */

public class MovePiece extends AsyncTask<String, String, String> {

    private String ip = "192.168.1.100";
    private int port = 15555;
    private WebView chessboard;

    MovePiece(WebView chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... movePoints) {

        String sendPoint = null;
        String returnPoint = null;

        if (movePoints != null){
            sendPoint = movePoints[0];
        }


        try {
            Socket socket = new Socket(ip, port);
            Log.d("chessPiece attach", "server");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            writer.println(sendPoint);
            writer.flush();

            returnPoint = reader.readLine();

            Log.d("chessPiece getMessage", returnPoint);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return returnPoint;
    }

    @Override
    protected void onPostExecute(String position){
        super.onPostExecute(position);
        Log.d("chessPiece",position);
        chessboard.loadUrl("javascript:movePiece('"+position+"');");
    }
}
