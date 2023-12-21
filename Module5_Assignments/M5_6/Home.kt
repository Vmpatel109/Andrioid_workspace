package com.example.m6_1

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
import com.example.jsonparsingpost.Login

class Home : AppCompatActivity()
{
    private lateinit var edit1:EditText
    private lateinit var edit2:EditText
    private lateinit var edit3:EditText
    private lateinit var edit4:EditText
    private lateinit var edit5:EditText
    private lateinit var but1:AppCompatButton
    private lateinit var but2:AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        edit1 = findViewById(R.id.edtfname)
        edit2 = findViewById(R.id.edtlname)
        edit3 = findViewById(R.id.edtemail)
        edit4 = findViewById(R.id.edtmob)
        edit5 = findViewById(R.id.edtpass)

        but1 = findViewById(R.id.btnsignup)
        but2 = findViewById(R.id.btnlogin)

        but1.setOnClickListener {

            val fname = edit1.text.toString()
            val lname = edit2.text.toString()
            val email = edit3.text.toString()
            val mobile = edit4.text.toString()
            val pass = edit5.text.toString()

            val stringRequest: StringRequest = object :StringRequest(
                Method.POST,"https://vyasprakruti.000webhostapp.com/InventorymanaementSystem/register.php",Response.Listener {

                Toast.makeText(applicationContext,"Signup Successful",Toast.LENGTH_LONG).show()

            },
                Response.ErrorListener {

                Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()

            })


            {
                override fun getParams(): MutableMap<String, String>
                {
                    val map = HashMap<String,String>()
                    map["firstname"]=fname
                    map["lastname"]=lname
                    map["email"]=email
                    map["mobile"]=mobile
                    map["password"]=pass
                    return map
                }
            }

            val queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringRequest)
        }

        but2.setOnClickListener {

           startActivity(Intent(applicationContext, Login::class.java))
        }

    }

}