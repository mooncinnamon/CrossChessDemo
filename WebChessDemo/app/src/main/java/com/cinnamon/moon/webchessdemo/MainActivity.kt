package com.cinnamon.moon.webchessdemo

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var message = findViewById<TextView>(R.id.text)
        var send = findViewById<Button>(R.id.button)

        send.setOnClickListener {
            var piece = movePiece(message)
            piece.execute()
        }

    }
}
