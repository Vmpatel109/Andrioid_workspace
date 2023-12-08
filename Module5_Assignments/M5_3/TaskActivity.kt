package com.example.m5_3


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.example.m5_3.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar

class TaskActivity : AppCompatActivity() {

    lateinit var edtTaskAdd:EditText
    lateinit var edtTaskDesc:EditText

    lateinit var floatingActionButton: FloatingActionButton
    lateinit var Linear1: LinearLayout
    lateinit var SpinList: Spinner
    lateinit var dbHelper: DbHelper
    lateinit var simpleDateFormat: SimpleDateFormat
    lateinit var  toolbar: Toolbar


    companion object
    {
        lateinit var edtTaskDate:EditText
        lateinit var edtTaskTime:EditText
    }
    var arr = arrayOf("PRIORITY", "LOW", "AVERAGE", "HIGH")

    @SuppressLint("SetTextI18n", "SimpleDateFormat", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        edtTaskAdd = findViewById(R.id.edtTask)
        edtTaskDesc = findViewById(R.id.edtTaskDesc)
        edtTaskDate = findViewById(R.id.edtTaskDate)
        edtTaskTime = findViewById(R.id.edtTaskTime)
        floatingActionButton = findViewById(R.id.f1)
        Linear1 = findViewById(R.id.l1)
        SpinList = findViewById(R.id.spinnerList)
        dbHelper = DbHelper(applicationContext)
        toolbar = findViewById(R.id.tool1)


        setSupportActionBar(toolbar)


        val adap =
            ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, arr)

        SpinList.adapter = adap

        SpinList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                floatingActionButton.setOnClickListener {

                    var m = Model()


                    var taskNm = edtTaskAdd.text.toString()
                    var taskDesc = edtTaskDesc.text.toString()
                    var taskDt = edtTaskDate.text.toString()
                    var taskTime = edtTaskTime.text.toString()

                    if (taskNm.toString().length == 0
                        && taskDesc.toString().length == 0
                        && taskDt.toString().length == 0
                        && taskTime.toString().length == 0
                        && m.task_priority.length == 0)
                    {
                        Snackbar.make(Linear1, "Enter Values", Snackbar.LENGTH_LONG).show()
                    }
                    else {
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
                                m.task_priority = "DUE TASK"
                            }
                        }
                        var data = dbHelper.insertdata(m)

                        Snackbar.make(Linear1, "Task Added::  " + data, Snackbar.LENGTH_LONG).show()

                        startActivity(Intent(applicationContext, AddTaskActivity::class.java))
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        edtTaskDate.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth ->
                    edtTaskDate.setText(dayOfMonth.toString() + "/ " + (monthOfYear + 1).toString() + "/ " + year)
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        edtTaskTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                    edtTaskTime.setText(SimpleDateFormat("HH:MM").format(cal.time))
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true)
                .show()
        }
        }
    }





