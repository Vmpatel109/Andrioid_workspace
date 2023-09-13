package com.example.m4_9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
    lateinit var tableLayout: TableLayout
    lateinit var txt1: TextView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tableLayout=TableLayout(this)
        txt1= TextView(this)
        txt1.setText("Vandit")
        var width=200
        var height=100

        tableLayout.addView(txt1,width,height)
        setContentView(tableLayout)
    }
}
