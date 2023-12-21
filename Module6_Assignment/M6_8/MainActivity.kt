package com.example.m6_8

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity()
{
    lateinit var imageView: ImageView
    lateinit var btn: Button
    lateinit var btn1:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ad = AnimationDrawable()
        imageView=findViewById(R.id.img)
        var f1 = resources.getDrawable(R.drawable.b1,null)
        var f2 = resources.getDrawable(R.drawable.b2,null)
        var f3 = resources.getDrawable(R.drawable.b3,null)
        var f4 = resources.getDrawable(R.drawable.b4,null)

        ad.addFrame(f1,200)
        ad.addFrame(f2,300)
        ad.addFrame(f3,400)
        ad.addFrame(f4,500)


        imageView.background=ad

        btn=findViewById(R.id.btn)
        btn1=findViewById(R.id.btn1)

        btn.setOnClickListener {
            ad.start()
        }
        btn1.setOnClickListener {
            ad.stop()
        }
    }
}