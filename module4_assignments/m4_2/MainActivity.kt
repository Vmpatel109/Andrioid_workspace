package com.example.m4_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity()
{
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var bnt1:Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        bnt1 = findViewById(R.id.btn1)

        bnt1.setOnClickListener {

            var a = edt1.text.toString()
            var b = edt2.text.toString()
            var c = 0


            var data1 = a.toInt()
            var data2 = b.toInt()
            var data3 = data1+1

            var sb = StringBuffer("")
            for (i in data3..data2)
            {
                sb.append(",")
                sb.append(i)

                var i2 = Intent(applicationContext, MainActivity2::class.java)
                i2.putExtra("data", sb.toString())
                startActivity(i2)
            }




    }
}
}