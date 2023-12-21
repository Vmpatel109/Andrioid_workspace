package com.example.m6_5

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class MyService : Service()
{

    override fun onBind(intent: Intent): IBinder
    {
        TODO("Return the communication channel to the service.")
    }
    override fun onStart(intent: Intent?, startId: Int)
    {
        super.onStart(intent, startId)
        Toast.makeText(applicationContext,"Started",Toast.LENGTH_LONG).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Toast.makeText(applicationContext,"Start command",Toast.LENGTH_LONG).show()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Toast.makeText(applicationContext,"Destroyed", Toast.LENGTH_LONG).show()

        super.onDestroy()
    }

}