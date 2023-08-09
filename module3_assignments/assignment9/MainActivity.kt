package com.example.module3assignment9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.module3assignment9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.btn1.setOnClickListener {

            //A to F

            var b1 = BlankFragment()
            var fm:FragmentManager = supportFragmentManager
            var ft:FragmentTransaction = fm.beginTransaction()
            ft.replace(R.id.frame,b1).commit()


        }


    }
    }

