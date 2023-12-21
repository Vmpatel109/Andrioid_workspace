package com.example.m6_1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.m6_1.Home
import com.example.m6_1.MainActivity
import com.example.m6_1.R

class Login : AppCompatActivity() {

    private lateinit var text1:EditText
    private lateinit var text2:EditText

    private lateinit var bt1:AppCompatButton
    private lateinit var bt2:AppCompatButton



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        text1 = findViewById(R.id.edtmob)
        text2 = findViewById(R.id.edtpass)

        bt1 = findViewById(R.id.btnlogin)
        bt2 = findViewById(R.id.btnsignup)

        bt1.setOnClickListener {

            val mobile = text1.text.toString()
            val pass = text2.text.toString()

            val stringRequest: StringRequest = object:StringRequest(
                Method.POST,"https://vyasprakruti.000webhostapp.com/InventorymanaementSystem/login.php", { response->

                try
                {
                    if(response.trim() == "0")
                    {
                        Toast.makeText(applicationContext,"Login fail",Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Login Success",Toast.LENGTH_LONG).show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    }
                }
                catch (e:java.lang.Exception)
                {
                    e.printStackTrace()
                }




            },
                Response.ErrorListener {

                Toast.makeText(applicationContext,"Login Fail",Toast.LENGTH_LONG).show()
            })

            {
                override fun getParams(): MutableMap<String, String> {

                    val map = HashMap<String,String>()

                    map["mobile"]=mobile
                    map["password"]=pass

                    return map
                }
            }

            val queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringRequest)

        }


        bt2.setOnClickListener {


            startActivity(Intent(applicationContext, Home::class.java))
        }

    }
}