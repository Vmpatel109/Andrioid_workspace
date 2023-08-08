package com.example.module3assignment4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(){
    lateinit var btn:Button
    lateinit var edt1:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.btn)
        edt1 = findViewById(R.id.edt1)
        btn.setOnClickListener {
            var name = edt1.text.toString()
            var i = Intent(applicationContext, MainActivity2::class.java)
            i.putExtra("name", name)
            startActivity(i)
        }
    }
}