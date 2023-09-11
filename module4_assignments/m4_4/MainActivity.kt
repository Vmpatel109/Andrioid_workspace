package com.example.m4_4

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.m4_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn1.setOnClickListener{
            var bt=BlankFragment()
            var fr:FragmentManager=supportFragmentManager
            var t:FragmentTransaction=fr.beginTransaction()
            t.replace(R.id.f1,bt).commit()

        }
        binding.btn2.setOnClickListener {

            var bt=BlankFragment2()
            var fr:FragmentManager=supportFragmentManager
            var t:FragmentTransaction=fr.beginTransaction()
            t.replace(R.id.f1,bt).commit()
        }

    }
}