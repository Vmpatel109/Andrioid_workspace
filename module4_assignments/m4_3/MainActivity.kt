package com.example.m4_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var rb:RadioGroup
    lateinit var rb1:RadioButton
    lateinit var rb2:RadioButton
    lateinit var rb3:RadioButton
    lateinit var rb4:RadioButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        rb1 = findViewById(R.id.rb1)
        rb2 = findViewById(R.id.rb2)
        rb3 = findViewById(R.id.rb3)
        rb4 = findViewById(R.id.rb4)


        rb1.setOnCheckedChangeListener(this)
        rb2.setOnCheckedChangeListener(this)
        rb3.setOnCheckedChangeListener(this)
        rb4.setOnCheckedChangeListener(this)


    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {

        var n1 = edt1.text.toString()
        var n2 = edt2.text.toString()
        var a= Integer.parseInt(n1)
        var b = Integer.parseInt(n2)
        var ans = 0

        if (rb1.isChecked)
        {
            ans = a+b
            Toast.makeText(applicationContext,""+ans,Toast.LENGTH_LONG).show()
        }
        else if (rb2.isChecked)
        {
            ans = a-b
            Toast.makeText(applicationContext,""+ans,Toast.LENGTH_LONG).show()
        }

        else if (rb3.isChecked)
        {
            ans = a*b
            Toast.makeText(applicationContext,""+ans,Toast.LENGTH_LONG).show()
        }
        else if (rb4.isChecked)
        {
            ans = b/a
            Toast.makeText(applicationContext,""+ans,Toast.LENGTH_LONG).show()
        }
    }
}