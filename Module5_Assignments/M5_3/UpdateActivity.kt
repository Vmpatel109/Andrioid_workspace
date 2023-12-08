package com.example.m5_3

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar

class UpdateActivity : AppCompatActivity() {

    lateinit var dbHelper: DbHelper
    lateinit var edtnm: EditText
    lateinit var edtdesc: EditText

    lateinit var SpinList: Spinner
    lateinit var floatingActionButton: FloatingActionButton

    companion object {
        lateinit var edtdt: EditText
        lateinit var edttime: EditText
    }

    var arr = arrayOf("PRIORITY", "LOW", "AVERAGE", "HIGH")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        edtnm = findViewById(R.id.edtTaskU)
        edtdesc = findViewById(R.id.edtTaskDescU)
        edtdt = findViewById(R.id.edtTaskDateU)
        edttime = findViewById(R.id.edtTaskTimeU)
        SpinList = findViewById(R.id.spinnerListU)

        floatingActionButton = findViewById(R.id.f1)

        var i = intent
        var id1 = i.getIntExtra("ID", 101)
        var task_name = i.getStringExtra("TASKNAME")
        var task_desc = i.getStringExtra("TASKDESC")
        var task_Date = i.getStringExtra("TASKDATE")
        var task_time = i.getStringExtra("TASKTIME")
        var task_prio = i.getStringExtra("TASKPRIO")


        edtnm.setText(task_name)
        edtdesc.setText(task_desc)
        edtdt.setText(task_Date)
        edttime.setText(task_time)

        dbHelper = DbHelper(applicationContext)


        val adap =
            ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, arr)

        SpinList.adapter = adap

        SpinList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                floatingActionButton.setOnClickListener {


                    var taskNm = edtnm.text.toString()
                    var taskDesc = edtdesc.text.toString()
                    var taskDt = edtdt.text.toString()
                    var taskTime = edttime.text.toString()

                    var m = Model()

                    m.id = id1
                    m.task_name = taskNm
                    m.task_description = taskDesc
                    m.task_date = taskDt
                    m.task_time = taskTime

                    when (position) {
                        1 -> {
                            m.task_priority = "LOW"
                        }

                        2 -> {
                            m.task_priority = "AVERAGE"
                        }

                        3 -> {
                            m.task_priority = "HIGH"
                        }

                        else -> {
                            m.task_priority = "PRIORITY"
                        }
                    }

                    var data = dbHelper.updatedata(m)

                    Toast.makeText(applicationContext, "Updated", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(applicationContext, AddTaskActivity::class.java))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        edtdt.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth ->

                    edtdt.setText(dayOfMonth.toString() + "/ " + (monthOfYear + 1).toString() + "/ " + year)
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        edttime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                edttime.setText(SimpleDateFormat("HH:MM").format(cal.time))
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true)
                .show()
        }
    }
}
