package com.example.rotate_image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        textView = findViewById(R.id.t1)
        button = findViewById(R.id.button)

        imageView.setOnClickListener(View.OnClickListener {


            val clk_rotate = AnimationUtils.loadAnimation(this,R.anim.anim_rotate)
            clk_rotate.duration
            clk_rotate.repeatCount = Animation.INFINITE
            imageView.startAnimation(clk_rotate)

        })

        button.setOnClickListener {

            val animation1= AnimationUtils.loadAnimation(this,R.anim.blink)
            textView.startAnimation(animation1)

        }
    }
}