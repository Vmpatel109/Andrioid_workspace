package com.example.m6_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jsonparsingpost.MainActivity2

class MainActivity : AppCompatActivity()
{

    private lateinit var p_name : EditText
    private lateinit var p_price :EditText
    private lateinit var p_description :EditText
    private lateinit var add: AppCompatButton
    private lateinit var view: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        p_name = findViewById(R.id.edit1)
        p_price = findViewById(R.id.edit2)
        p_description = findViewById(R.id.edit3)
        add = findViewById(R.id.bt1)
        view = findViewById(R.id.bt2)

        add.setOnClickListener {

            val name = p_name.text.toString()
            val price = p_price.text.toString()
            val des = p_description.text.toString()

            val stringRequest:StringRequest = object: StringRequest(
                Request.Method.POST,"https://vyasprakruti.000webhostapp.com/InventorymanaementSystem/productinsert.php",
                Response.Listener {

                    Toast.makeText(applicationContext,"Product Added",Toast.LENGTH_LONG).show()

                }, Response.ErrorListener {
                    Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()

                })
            {
                override fun getParams(): MutableMap<String, String> {

                    val map = HashMap<String,String>()
                    map["product_name"]=name
                    map["product_price"]=price
                    map["product_description"]=des

                    return map
                }
            }
            val queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringRequest)



            p_name.text.clear()
            p_price.text.clear()
            p_description.text.clear()


        }
        view.setOnClickListener {

            val i = Intent(applicationContext, MainActivity2::class.java)
            startActivity(i)

        }


    }
}