package com.example.jsonregisterlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jsonregisterlogin.databinding.ActivityAddProductBinding

class AddProductActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btninsert.setOnClickListener {

            var pname = binding.edtpname.text.toString()
            var pprice = binding.edtpprice.text.toString()
            var pdes = binding.edtdes.text.toString()


            var stringRequest: StringRequest = object : StringRequest(
                Request.Method.POST,
                "https://vyasprakruti.000webhostapp.com/InventorymanaementSystem/productinsert.php",
                Response.Listener {

                    Toast.makeText(applicationContext, "Product Added", Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext,DashboardActivity::class.java))
                },
                Response.ErrorListener {

                    Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_LONG).show()

                }) {
                override fun getParams(): MutableMap<String, String>? {
                    var map = HashMap<String, String>()
                    map["product_name"] = pname
                    map["product_price"] = pprice
                    map["product_description"] = pdes

                    return map
                }
            }

            var queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringRequest)

        }
    }
}