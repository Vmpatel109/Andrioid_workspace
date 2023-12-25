package com.example.m7_2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)

        mediaPlayer= MediaPlayer()
        val ring: MediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.jamal)

        btn1.setOnClickListener {

            ring.start()

        }

        btn2.setOnClickListener {

            ring.pause()

        }


    }
}