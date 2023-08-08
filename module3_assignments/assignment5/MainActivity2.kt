package com.example.module3assignment5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    lateinit var data1:TextView
    lateinit var data2:TextView
    lateinit var data3:TextView
    lateinit var data4:TextView
    lateinit var data5:TextView
    lateinit var data6: TextView

    lateinit var bar: RatingBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        data1=findViewById(R.id.a1)
        data2=findViewById(R.id.a2)
        data3=findViewById(R.id.a3)
        data4=findViewById(R.id.a4)
        data5=findViewById(R.id.a5)
        data6=findViewById(R.id.a6)
        bar=findViewById(R.id.rbar)

        var i1 =intent
        data1.setText("FIRST NAME:"+i1.getStringExtra("data1"))
        data2.setText("LAST NAME:"+i1.getStringExtra("data2"))
        data3.setText("EMAIL:"+i1.getStringExtra("data3"))
        data4.setText("MOBILE NO :"+i1.getStringExtra("data4"))
        data6.setText("PASSWORD :"+i1.getStringExtra("data6"))


        bar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            Toast.makeText(applicationContext, ""+ratingBar.rating, Toast.LENGTH_SHORT).show()

        }

    }
}