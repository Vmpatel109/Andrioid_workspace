package com.example.m4_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), TextWatcher {

    lateinit var edt1:EditText
    lateinit var txt1:TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt1 = findViewById(R.id.edt1)
        txt1 = findViewById(R.id.txt1)

        edt1.addTextChangedListener(this)

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {

        var a1 = edt1.text.toString().toInt()
        var rev = 0
        var temp = 0
        while (a1!=0)
        {
            temp = a1%10
            rev = rev*10+temp
            a1/=10
        }
        Toast.makeText(applicationContext,""+rev,Toast.LENGTH_LONG).show()
    }
}