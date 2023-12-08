package com.example.m5_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.stickynotes_m5_a2.DbHelper

class UpdateActivity : AppCompatActivity()
{
    lateinit var dbHelper : DbHelper
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        dbHelper = DbHelper(applicationContext)

        showAlertDialog()
    }


    private fun showAlertDialog() {

        var m = Model()
        var alertDialog = AlertDialog.Builder(this)
        var view = layoutInflater.inflate(R.layout.design, null)
        alertDialog.setView(view)
        var editText1 = view.findViewById<EditText>(R.id.txt_Title)
        var editText2 = view.findViewById<EditText>(R.id.txt_Note)

        var i = intent
        var id = i.getIntExtra("ID",101)
        var titleU =  i.getStringExtra("TITLES")
        var noteU=  i.getStringExtra("NOTES")

        editText1.setText(titleU)
        editText2.setText(noteU)


        alertDialog.setPositiveButton("OK") { dialog, which -> // send data from the AlertDialog to the Activity


            var nametitle = editText1.text.toString()
            var namenote = editText2.text.toString()

            var m = Model()
            m.id=id
            m.title=nametitle
            m.note=namenote

            var data =  dbHelper.updatedata(m)


            startActivity(Intent(applicationContext, TaskActivity::class.java))
        }

        val alert = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}