package com.example.m4_6

import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        val connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!
            .state == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED

        if(connected)
        {
            var layout= LayoutInflater.from(applicationContext)
            var view=layout.inflate(R.layout.design,null)
            var t= Toast(applicationContext)
            t.view=view
            t.duration=Toast.LENGTH_LONG
            t.setGravity(Gravity.CENTER,0,0)
            t.show()

        }
        else
        {
            var layout=LayoutInflater.from(applicationContext)
            var view=layout.inflate(R.layout.design2,null)
            var t1=Toast(applicationContext)
            t1.view=view
            t1.duration=Toast.LENGTH_LONG
            t1.setGravity(Gravity.CENTER,0,0)
            t1.show()
        }
    }
    }
