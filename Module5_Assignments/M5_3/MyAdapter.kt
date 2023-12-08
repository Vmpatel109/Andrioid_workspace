package com.example.m5_3

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView

class MyAdapter(var context: Context, var listTask: MutableList<Model>) : BaseAdapter()
{
    val filteredlist = ArrayList<Model>()

    init {
        filteredlist.addAll(listTask)
    }

    override fun getCount(): Int {
        return filteredlist.size
    }

    override fun getItem(position: Int): Any {
        return filteredlist.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var layoutInflater = LayoutInflater.from(context)
        var view = layoutInflater.inflate(R.layout.design, parent, false)

        var txt_TaskName: TextView = view.findViewById(R.id.txt_TaskName)
        var txt_TaskDesc: TextView = view.findViewById(R.id.txt_TaskDesc)
        var txt_TaskDate: TextView = view.findViewById(R.id.txt_TaskDate)
        var txt_TaskTime: TextView = view.findViewById(R.id.txt_TaskTime)
        var txt_TaskPrio: TextView = view.findViewById(R.id.txt_TaskPrio)

        var cardView : CardView =  view.findViewById(R.id.cardview1)


        txt_TaskName.setText(listTask.get(position).task_name)
        txt_TaskDesc.setText(listTask.get(position).task_description)
        txt_TaskDate.setText(listTask.get(position).task_date)
        txt_TaskTime.setText(listTask.get(position).task_time)
        txt_TaskPrio.setText(listTask.get(position).task_priority)



        if(listTask.get(position).task_priority == "LOW")
        {
            cardView.setCardBackgroundColor(Color.parseColor("#023020"))
        }
        if(listTask.get(position).task_priority == "AVERAGE")
        {
            cardView.setCardBackgroundColor(Color.parseColor("#00238b"))
        }
        if(listTask.get(position).task_priority == "HIGH")
        {
            cardView.setCardBackgroundColor(Color.parseColor("#8b0000"))
        }

        if(listTask.get(position).task_priority == "DUE TASK")
        {
            cardView.setCardBackgroundColor(Color.parseColor("#F2921D"))
        }

        val item = filteredlist[position]
        txt_TaskDate.text = item.task_date

        return view


    }

    fun filterDate(query : String){

        filteredlist.clear()

        if(query.isEmpty())
        {
            filteredlist.addAll(listTask)
        }

        else
        {
            val date = query
            for(item in listTask)
            {
                if(item.task_date.contains(date))
                {
                    filteredlist.add(item)
                }
            }

        }
        notifyDataSetChanged()
    }

}