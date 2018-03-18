package com.cinnamon.moon.webchessdemo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

/**
 * Created by moonyoubin on 2018. 3. 16..
 */

public class MainActivity extends AppCompatActivity {

    private final Handler handler = new Handler();
    private MovePiece movePiece;
    private WebView chessboard;
    private Button mBfinishGame;

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chessboard = findViewById(R.id.chessboard);
        mBfinishGame = findViewById(R.id.finishGame);

        WebView.setWebContentsDebuggingEnabled(true);



        mBfinishGame.setOnClickListener(finishGame);

        WebSettings settings = chessboard.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        chessboard.addJavascriptInterface(new GetPiece(), "Position");

        chessboard.loadUrl("file:///android_asset/www/index.html");
    }

    private View.OnClickListener finishGame = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            movePiece = new MovePiece(chessboard);
            movePiece.execute("#finishGame");
        }
    };

    private class GetPiece{
        @JavascriptInterface
        public void getPosition(final String position){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    movePiece = new MovePiece(chessboard);
                    movePiece.execute(position);
                }
            });
        }
    }
}
