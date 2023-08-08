package com.example.module3assignment7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var txt1:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext,"Created",Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext,"start",Toast.LENGTH_LONG).show()
    }

       override fun onStop() {
            super.onStop()
            Toast.makeText(applicationContext,"stop",Toast.LENGTH_LONG).show()
        }
      override fun onDestroy(){
            super.onDestroy()
            Toast.makeText(applicationContext,"Destroy",Toast.LENGTH_LONG).show()
        }
      override fun onResume(){
            super.onResume()
            Toast.makeText(applicationContext,"Resume",Toast.LENGTH_LONG).show()
        }
      override fun onPause(){
            super.onPause()
            Toast.makeText(applicationContext,"Pause",Toast.LENGTH_LONG).show()

        }
      override fun onRestart(){
            super.onRestart()
            Toast.makeText(applicationContext,"Restart",Toast.LENGTH_LONG).show()
        }

    }

