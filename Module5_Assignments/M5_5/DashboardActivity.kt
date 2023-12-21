package com.example.jsonregisterlogin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray

class DashboardActivity : AppCompatActivity()
{

    lateinit var sharedPreferences: SharedPreferences
    lateinit var f1: FloatingActionButton
    lateinit var recyclerView: RecyclerView
    lateinit var list:MutableList<Model>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_borad)


        f1 = findViewById(R.id.f1)
        recyclerView = findViewById(R.id.recycler)
        list = ArrayList()
        sharedPreferences = getSharedPreferences("USER_SESSION", Context.MODE_PRIVATE)
        Toast.makeText(applicationContext,"Welcome "+sharedPreferences.getString("mob",""),Toast.LENGTH_LONG).show()

        var manager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager



        var stringRequest = StringRequest(
            Request.Method.GET,"https://vyasprakruti.000webhostapp.com/InventorymanaementSystem/productview.php",
            {
                    response->
                try
                {
                    var jsonArray = JSONArray(response)
                    for(i in 0 until jsonArray.length())
                    {
                        var jsonObject = jsonArray.getJSONObject(i)
                        var pid = jsonObject.getInt("product_id")
                        var pname = jsonObject.getString("product_name")
                        var pprice = jsonObject.getString("product_price")


                        var m = Model()
                        m.pid=pid
                        m.pname=pname
                        m.pprice=pprice

                        list.add(m)

                    }


                }
                catch(e:Exception)
                {
                    e.printStackTrace()
                }

                var myAdapter = MyAdapter(applicationContext,list)
                recyclerView.adapter=myAdapter
            },
            {
                Toast.makeText(applicationContext,"No Internet", Toast.LENGTH_LONG).show()
            })

        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringRequest)






        f1.setOnClickListener {

            startActivity(Intent(applicationContext,AddProductActivity::class.java))

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.logout->
            {
                sharedPreferences.edit().clear().commit()
                finish()
                startActivity(Intent(applicationContext,LoginActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)

    }
}