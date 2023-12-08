package com.example.m5_2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.stickynotes_m5_a2.DbHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskActivity : AppCompatActivity()
{
    lateinit var btnFloting: FloatingActionButton
    lateinit var dbHelper: DbHelper


    lateinit var listView: ListView
    lateinit var list:MutableList<Model>


    var arraylist:ArrayList<HashMap<String,String>> = ArrayList<HashMap<String,String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        btnFloting = findViewById(R.id.f1)
        dbHelper = DbHelper(applicationContext)

        list = ArrayList()
        listView = findViewById(R.id.list)

        list = dbHelper.viewdata()

        for(i in list)
        {
            var map = HashMap<String,String>()
            map.put("TITLE",i.title)
            map.put("NOTE",i.note)

            arraylist.add(map)
        }

        var from = arrayOf("TITLE","NOTE")
        var to = intArrayOf(R.id.txt1 ,R.id.txt2)


        var adapter = SimpleAdapter(applicationContext,arraylist,R.layout.design_view_data,from,to)
        listView.adapter=adapter

        listView.setOnCreateContextMenuListener(this)

        btnFloting.setOnClickListener {
            showAlertDialog()

        }

    }

    private fun showAlertDialog() {

        var m = Model()
        var alertDialog = AlertDialog.Builder(this)
        var view = layoutInflater.inflate(R.layout.design, null)
        alertDialog.setView(view)
        var editText1 = view.findViewById<EditText>(R.id.txt_Title)
        var editText2 = view.findViewById<EditText>(R.id.txt_Note)

        alertDialog.setPositiveButton("OK") { dialog, which -> // send data from the AlertDialog to the Activity

            m.title = editText1.text.toString()
            m.note = editText2.text.toString()


            var data =  dbHelper.insertdata(m)
            Toast.makeText(applicationContext, "Added  " + data , Toast.LENGTH_LONG).show()

            startActivity(Intent(applicationContext, TaskActivity::class.java))
        }

        val alert = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {

        var m1: MenuItem = menu!!.add(0,1,0,"Update")
        var m2:MenuItem = menu!!.add(0,2,0,"Delete")

        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean
    {
        var acm: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        var pos = acm.position
        var m = list[pos]

        when(item.itemId)
        {
            1->
            {
                var i = Intent(applicationContext, UpdateActivity::class.java)
                i.putExtra("ID",m.id)
                i.putExtra("TITLES",m.title)
                i.putExtra("NOTES",m.note)


                startActivity(i)
            }
            2->
            {
                var alert = AlertDialog.Builder(this)
                alert.setTitle("Are you Sure you want to delete?")
                alert.setPositiveButton("YES",{ dialogInterface: DialogInterface, i: Int ->


                    dbHelper.deleetdata(m.id)
                    startActivity(Intent(applicationContext,   TaskActivity::class.java))

                })
                alert.setNegativeButton("NO",{ dialogInterface: DialogInterface, i: Int ->

                    dialogInterface.cancel()

                })
                alert.show()


            }
        }

        return super.onContextItemSelected(item)
    }

    override fun onBackPressed() {

        finishAffinity()
        super.onBackPressed()
    } }
