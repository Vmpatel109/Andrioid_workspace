package com.example.m6_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.m6_1.R
import org.json.JSONArray
import kotlin.Exception

class MainActivity2 : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var list: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        listView = findViewById(R.id.listview1)
        list= ArrayList()

        val stringRequest = StringRequest(Request.Method.GET,"https://vyasprakruti.000webhostapp.com/InventorymanaementSystem/productview.php",{

                         response ->
                            try {
                                val jsonArray = JSONArray(response)
                                    for(i in 0 until jsonArray.length())
                                    {
                                        val jsonObject = jsonArray.getJSONObject(i)
                                        val name = jsonObject.getString("product_name")
                                        val price = jsonObject.getString("product_price")


                                        val p = Product()
                                        p.name = name
                                        p.price = price

                                        list.add(p)
                                    }

                            }catch (e:Exception)
                            {
                                e.printStackTrace()
                            }

            val myAdapter= MyAdapter(applicationContext,list)
            listView.adapter = myAdapter


        },{

            Toast.makeText(applicationContext,"Product list Not Available", Toast.LENGTH_LONG).show()

        })
        val queue =Volley.newRequestQueue(this)
        queue.add(stringRequest)
    }
}