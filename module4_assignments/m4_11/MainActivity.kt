package com.example.m4_11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
    lateinit var t1:TextView
    lateinit var b1:Button
    lateinit var b2:Button
    private var counter=0f
    private var size: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        t1 = findViewById(R.id.t1)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)

        counter = pixelsToSp(t1.textSize)

        b1.setOnClickListener {
            changeText(true)
        }

        b2.setOnClickListener {
            if(counter > 0) changeText(false)
        }
    }

    private fun changeText(incement: Boolean){
        size = if(incement) ++counter
        else --counter

        t1.textSize = size
    }

    fun pixelsToSp(px: Float): Float {
        val scaledDensity = resources.displayMetrics.scaledDensity
        return px / scaledDensity
    }
}
