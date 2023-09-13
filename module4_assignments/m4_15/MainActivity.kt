package com.example.m4_15

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import com.example.m4_15.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.a.setOnCheckedChangeListener(this)
        binding.b.setOnCheckedChangeListener(this)
        binding.c.setOnCheckedChangeListener(this)
        binding.d.setOnCheckedChangeListener(this)

    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        if (binding.a.isChecked) {
            binding.layout.setBackgroundColor(Color.YELLOW)
        }
        if (binding.b.isChecked) {
            binding.layout.setBackgroundColor(Color.BLUE)
        }
        if (binding.c.isChecked) {
            binding.layout.setBackgroundColor(Color.GREEN)
        }
        if (binding.d.isChecked) {
            binding.layout.setBackgroundColor(Color.RED)
        }
    }
}



