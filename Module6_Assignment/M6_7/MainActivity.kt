package com.example.m6_7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
    lateinit var textView: TextView
    lateinit var imageView: ImageView
    lateinit var zoomInButton: Button
    lateinit var zoomOutButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)
        zoomInButton = findViewById(R.id.zoomInButton)
        zoomOutButton = findViewById(R.id.zoomOutButton)

        zoomInButton.setOnClickListener() {


            val animZoomIn = AnimationUtils.loadAnimation(this,
                R.anim.zoom_in)
            // assigning that animation to
            // the image and start animation
            imageView.startAnimation(animZoomIn)
        }

        // actions to be performed when
        // "Zoom Out" button is clicked
        zoomOutButton.setOnClickListener() {

            // loading the animation of
            // zoom_out.xml file into a variable
            val animZoomOut = AnimationUtils.loadAnimation(this,
                R.anim.zoom_out)

            // assigning that animation to
            // the image and start animation
            imageView.startAnimation(animZoomOut)
        }
    }
}