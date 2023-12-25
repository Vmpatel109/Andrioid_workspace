package com.example.m7_7

import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.wifiSwitch)
        val textView = findViewById<TextView>(R.id.tv)


        val wifi = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager

        btn.setOnClickListener {
            wifi.isWifiEnabled = !wifi.isWifiEnabled
            if (!wifi.isWifiEnabled) {
                textView.text = "Wifi is OFF"
            } else {
                textView.text = "Wifi is ON"
            }
        }
    }
}