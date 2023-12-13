package com.example.jsonregisterlogin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jsonregisterlogin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPreferences = getSharedPreferences("USER_SESSION", Context.MODE_PRIVATE)

        if(sharedPreferences.getBoolean("USER_SESSION",false) && !sharedPreferences.getString("mob","")!!.isEmpty())
        {
            startActivity(Intent(this,DashboardActivity::class.java))
            finish()
        }

        binding.btnlogin.setOnClickListener {

            var mobile = binding.edtmob.text.toString()
            var pass = binding.edtpass.text.toString()


            var stringRequest: StringRequest = object:StringRequest(Request.Method.POST,"https://vyasprakruti.000webhostapp.com/InventorymanaementSystem/login.php", { response->

                try
                {
                    if(response.trim().equals("0"))
                    {
                        Toast.makeText(applicationContext,"Login fail",Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Login Success",Toast.LENGTH_LONG).show()
                        var editor:SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putBoolean("USER_SESSION",true)
                        editor.putString("mob",mobile)
                        editor.putString("pass",pass)
                        editor.commit()
                        startActivity(Intent(applicationContext,DashboardActivity::class.java))
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
                override fun getParams(): MutableMap<String, String>? {

                    var map = HashMap<String,String>()

                    map["mobile"]=mobile
                    map["password"]=pass

                    return map
                }
            }
            var queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringRequest)

        }
        binding.btnsignup.setOnClickListener {

            startActivity(Intent(applicationContext,MainActivity::class.java))

        }

    }
}