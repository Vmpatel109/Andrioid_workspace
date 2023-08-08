package com.example.module3assignment5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var txt1: TextView
    lateinit var txt2: TextView
    lateinit var txt3: TextView
    lateinit var txt4: TextView

    lateinit var ch1: CheckBox
    lateinit var ch2: CheckBox
    lateinit var ch3: CheckBox

    lateinit var rb1: RadioButton
    lateinit var rb2: RadioButton

    lateinit var spin: Spinner
    var city = arrayOf("Rajkot", "Ndiad", "Jamnagar", "Ahemdabad")

    lateinit var txt5: TextView
    lateinit var txt6: TextView
    lateinit var btn: Button
    var cdata = ""
    var cdata2 = ""
    var cdata3 = ""
    var gender = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt1 = findViewById(R.id.e1)
        txt2 = findViewById(R.id.e2)
        txt3 = findViewById(R.id.e3)
        txt4 = findViewById(R.id.e4)
        txt5 = findViewById(R.id.e8)
        txt6 = findViewById(R.id.e9)

        btn = findViewById(R.id.btn)

        ch1 = findViewById(R.id.e5)
        ch2 = findViewById(R.id.e10)
        ch3 = findViewById(R.id.e11)

        rb1 = findViewById(R.id.rb1)
        rb2 = findViewById(R.id.rb2)
        spin = findViewById(R.id.e7)

        var adapter =
            ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, city)
        spin.adapter = adapter

        btn.setOnClickListener {
            var fname = txt1.text.toString()
            var lname = txt2.text.toString()
            var email = txt3.text.toString()
            var mno = txt4.text.toString()
            var pass = txt5.text.toString()
            var cpass = txt6.text.toString()





            if (fname.length == 0 && lname.length == 0 && email.length == 0 && mno.length == 0 && pass.length == 0 && cpass.length == 0) {
                txt1.setError("Please enter first name")
                txt2.setError("Please enter last name")
                txt3.setError("Please enter email")
                txt4.setError("Please enter mobile no")
                txt5.setError("Please enter password")
                txt6.setError("Please enter conf. password")
            } else if (fname.length == 0) {
                txt1.setError("Please enter first name")
            } else if (lname.length == 0) {
                txt2.setError("Please enter last name")
            } else if (email.length == 0) {
                txt3.setError("Please enter email")
            } else if (mno.length == 0) {
                txt4.setError("Please enter mobile no")
            } else if (mno.length <= 9) {
                txt4.setError("Enter 10 digit number")
            } else if (mno.length >= 11) {
                txt4.setError("Enter 10 digit number")
            } else if (pass.length == 0) {
                txt5.setError("Please enter password")
            } else if (cpass.length == 0) {
                txt6.setError("Please enter conf. password")
            } else if (cpass != pass) {
                txt6.setError("Please check your conf. password")
            }
            //else if ()
            else {
                Toast.makeText(applicationContext, " Sign-up successfully", Toast.LENGTH_SHORT)
                    .show()

                var builder = StringBuilder()
                if (ch1.isChecked) {
                    cdata = "playing"
                    builder.append(cdata)
                }
                if (ch2.isChecked) {
                    cdata2 = "travelling"
                    builder.append(cdata2)
                }
                if (ch3.isChecked) {
                    cdata3 = "Reading"
                    builder.append(cdata3)
                }

                if (rb1.isChecked) {
                    gender = "Male"
                }
                if (rb2.isChecked) {
                    gender = "Female"
                }


                var i1 = Intent(applicationContext, MainActivity2::class.java)
                i1.putExtra("data1", fname)
                i1.putExtra("data2", lname)
                i1.putExtra("data3", email)
                i1.putExtra("data4", mno)
                i1.putExtra("data5", builder.toString())
                i1.putExtra("data6", pass)
                i1.putExtra("data7", gender)
                startActivity(i1)


            }

        }
    }
}