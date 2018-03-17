package com.cinnamon.moon.webchessdemo;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by moonyoubin on 2018. 3. 16..
 */

public class MainActivity extends AppCompatActivity {

    private final Handler handler = new Handler();

    @SuppressLint("JavascriptInterface")
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView chessboard = findViewById(R.id.chessboard);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            chessboard.setWebContentsDebuggingEnabled(true);
        }
        WebSettings settings = chessboard.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        chessboard.addJavascriptInterface(new GetPiece(), "Position");

        chessboard.loadUrl("file:///android_asset/www/index.html");
    }

    private class GetPiece{
        @JavascriptInterface
        public void getPosition(final String position){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.d("Move", position);
                }
            });
        }
    }
}
