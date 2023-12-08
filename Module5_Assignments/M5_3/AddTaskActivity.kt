package com.example.m5_3



import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class AddTaskActivity : AppCompatActivity() {

    lateinit var  f1:FloatingActionButton
    lateinit var dbHelper: DbHelper
    lateinit var layout1:LinearLayout
    lateinit var listView: ListView
    lateinit var list:MutableList<Model>
    lateinit var  toolbar1: Toolbar
  /*  lateinit var searchView1:SearchView*/

    var arraylist:ArrayList<HashMap<String,String>> = ArrayList<HashMap<String,String>>()

    @SuppressLint("MissingInflatedId", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        f1 = findViewById(R.id.f1)
        dbHelper = DbHelper(applicationContext)

        list = ArrayList()
        listView = findViewById(R.id.list)
        layout1 = findViewById(R.id.layout1)
        toolbar1 = findViewById(R.id.tool1)


       /* searchView1 = findViewById(R.id.search1)
*/
        setSupportActionBar(toolbar1)
        toolbar1.setTitle("TASKS")

        list = dbHelper.viewdata()


        if(list.isEmpty())
        {
           Snackbar.make(layout1,"Please Add Task",Snackbar.LENGTH_SHORT).show()
        }



        var adapter = MyAdapter(applicationContext, list)
        listView.adapter = adapter


        listView.setOnCreateContextMenuListener(this)


        f1.setOnClickListener {
            startActivity(Intent(applicationContext,TaskActivity::class.java))
        }
    }


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {

        var m1: MenuItem = menu!!.add(0,1,0,"Complete Task")
        var m2:MenuItem = menu!!.add(0,2,0,"Update Task")
        var m3:MenuItem = menu!!.add(0,3,0,"Delete Task")

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
                dbHelper.deletedata(m.id)

                startActivity(Intent(applicationContext,AddTaskActivity::class.java))

                Snackbar.make(layout1, "Task Completed", Snackbar.LENGTH_INDEFINITE).show()
            }
            2->
            {
                var i = Intent(applicationContext, UpdateActivity::class.java)
                i.putExtra("ID",m.id)
                i.putExtra("TASKNAME",m.task_name)
                i.putExtra("TASKDESC",m.task_description)
                i.putExtra("TASKDATE",m.task_date)
                i.putExtra("TASKTIME",m.task_time)
                i.putExtra("TASKPRIO",m.task_priority)


                startActivity(i)
            }
            3->
            {
                var alert = AlertDialog.Builder(this)
                alert.setTitle("Are you Sure you want to delete?")
                alert.setPositiveButton("YES",{ dialogInterface: DialogInterface, i: Int ->


                    dbHelper.deletedata(m.id)
                    startActivity(Intent(applicationContext,AddTaskActivity::class.java))

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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.option,menu)


        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.sort_date ->
            {
                dbHelper.sort_date()
                Toast.makeText(applicationContext, "DATE CALLED", Toast.LENGTH_SHORT).show()
            }

        }

        return super.onOptionsItemSelected(item)
    }


}
