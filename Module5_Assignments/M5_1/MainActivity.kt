package com.example.m5_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.m5_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    lateinit var dbHelper:DbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        dbHelper= DbHelper(applicationContext)

        binding.btn1.setOnClickListener {

            var nm=binding.i1.text.toString()
            var num=binding.i2.text.toString()

            var mdl=Model()
            mdl.name=nm
            mdl.number=num

            var id = dbHelper.insertdata(mdl)

            Toast.makeText(applicationContext, "record inserted"+id, Toast.LENGTH_SHORT).show()
        }
        binding.btn2.setOnClickListener {
            var i= Intent(applicationContext,MainActivity2::class.java)
            startActivity(i)
        }
    }
}