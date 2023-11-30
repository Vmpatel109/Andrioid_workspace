package com.example.m5_1

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import com.example.m5_1.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity()
{
    private lateinit var binding: ActivityMain2Binding
    lateinit var dbHelper: DbHelper
    lateinit var mutableList: MutableList<Model>
    var arrayList: ArrayList<HashMap<String, String>> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dbHelper = DbHelper(applicationContext)
        mutableList = ArrayList()

        mutableList = dbHelper.viewdata()

        for (i in mutableList) {
            var hm = hashMapOf<String, String>()
            hm["name"] = i.name
            hm["number"] = i.number

            arrayList.add(hm)
        }
        var fromarray = arrayOf("name", "number")
        var toarray = intArrayOf(R.id.txt1, R.id.txt2)

        var adapter =
            SimpleAdapter(applicationContext, arrayList, R.layout.design, fromarray, toarray)
        binding.lv.adapter = adapter

        registerForContextMenu(binding.lv)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        var m1 = menu!!.add(1, 1, 1, "UPDATE")
        var m2 = menu!!.add(2, 2, 2, "DELETE")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var acm: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        var a = acm.position
        var m: Model = mutableList.get(a)

        when (item.itemId)
        {
            1->
            {
                var i = Intent(applicationContext,MainActivity3::class.java)
                i.putExtra("ID",m.id)
                i.putExtra("NAME",m.name)
                i.putExtra("NUM",m.number)
                startActivity(i)
            }
            2->
            {
                var alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Are you sure you want to delete?")
                alertDialog.setPositiveButton("YES", { dialogInterface: DialogInterface, i: Int ->

                    dbHelper.deletedata(m.id)
                    var i = Intent(applicationContext,MainActivity2::class.java)
                    startActivity(i)
                })
                alertDialog.setNegativeButton("NO",{ dialogInterface: DialogInterface, i: Int ->

                    dialogInterface.cancel()
                })
                alertDialog.show()

            }

        }
        return super.onContextItemSelected(item)
    }
    }
