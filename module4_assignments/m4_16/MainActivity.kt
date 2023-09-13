package com.example.m4_16

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SeekBar
import com.example.m4_16.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.s1.setOnSeekBarChangeListener(this)
        binding.s2.setOnSeekBarChangeListener(this)
        binding.s3.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        var a= binding.s1.progress
        var b= binding.s2.progress
        var c= binding.s3.progress
        binding.img.setBackgroundColor(Color.rgb(a,b,c))
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }
}