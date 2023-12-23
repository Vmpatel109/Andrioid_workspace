package com.example.m6_9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView

class MainActivity : AppCompatActivity()
{
    lateinit var img:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img = findViewById(R.id.img)

        Handler().postDelayed(Runnable {
            var i = Intent(applicationContext,MainActivity2::class.java)
            startActivity(i)

        },2000)
    }
}