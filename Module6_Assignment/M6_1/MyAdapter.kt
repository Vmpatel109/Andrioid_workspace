package com.example.m6_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.m6_1.R

class MyAdapter(private val context: Context,private val list: MutableList<Product>):BaseAdapter()
{



    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
     return list[position]
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layout=LayoutInflater.from(context)
        val view = layout.inflate(R.layout.design,parent,false)

        val p_name:TextView = view.findViewById(R.id.name)
        val p_price:TextView = view.findViewById(R.id.price)



        p_name.text =list[position].name
        p_price.text = list[position].price


        return view
    }
}