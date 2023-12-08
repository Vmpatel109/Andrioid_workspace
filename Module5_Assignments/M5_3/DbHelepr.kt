package com.example.m5_3


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbHelper(context: Context) : SQLiteOpenHelper (context, DB_NAME, null, DB_VERSION) {

    companion object {
        var DB_NAME = "task.db"
        var TABLE_NAME = "task_management"

        var ID = "id"
        var TASK_ADD = "task_add"
        var TASK_DESCRIPTION = "task_description"
        var TASK_DATE = "task_date"
        var TASK_TIME = "task_time"
        var TASK_PRIORITY = "task_priority"
        // var TASK_DONE = "task_complete"
        var DB_VERSION = 4
    }


    override fun onCreate(p0: SQLiteDatabase?) {
        var query =
            "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY,$TASK_ADD TEXT,$TASK_DESCRIPTION TEXT,$TASK_DATE TEXT,$TASK_TIME TEXT,$TASK_PRIORITY TEXT)"
        p0!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        var upquery = "DROP TABLE if exists $TABLE_NAME"
        p0!!.execSQL(upquery)
        onCreate(p0)
    }


    fun insertdata(m: Model): Long {
        var db = writableDatabase // you have to write something for data insertion

        var values = ContentValues()//add all data inside values

        values.put(TASK_ADD, m.task_name)
        values.put(TASK_DESCRIPTION, m.task_description)
        values.put(TASK_DATE, m.task_date)
        values.put(TASK_TIME, m.task_time)
        values.put(TASK_PRIORITY, m.task_priority)

        var id = db.insert(TABLE_NAME, ID, values)

        return id
    }



    fun viewdata(): MutableList<Model> {

        var db = readableDatabase
        var list: ArrayList<Model> = ArrayList<Model>()
        var col = arrayOf(ID, TASK_ADD, TASK_DESCRIPTION, TASK_DATE, TASK_TIME, TASK_PRIORITY)
        var cursor: Cursor = db.query(TABLE_NAME, col, null, null, null, null, null, null)
        //select * from tablename

        while (cursor.moveToNext()) {
            var id = cursor.getInt(0)
            var tasknm1 = cursor.getString(1)
            var taskdescription1 = cursor.getString(2)
            var taskdt1 = cursor.getString(3)
            var tasktime1 = cursor.getString(4)
            var taskpriority1 = cursor.getString(5)



            var m = Model()
            m.id = id
            m.task_name = tasknm1
            m.task_description = taskdescription1
            m.task_date = taskdt1
            m.task_time = tasktime1
            m.task_priority = taskpriority1


            list.add(m)
        }
        return list
    }

    fun sort_date(): MutableList<Model> {

        var db = readableDatabase
        var list: ArrayList<Model> = ArrayList<Model>()
        var col = arrayOf(ID, TASK_ADD, TASK_DESCRIPTION, TASK_DATE, TASK_TIME, TASK_PRIORITY)
        var cursor: Cursor = db.query(TABLE_NAME, col, null, null, null, null, TASK_DATE + " ASC", null)
        //select * from tablename

        while (cursor.moveToNext()) {
            var id = cursor.getInt(0)
            var tasknm1 = cursor.getString(1)
            var taskdescription1 = cursor.getString(2)
            var taskdt1 = cursor.getString(3)
            var tasktime1 = cursor.getString(4)
            var taskpriority1 = cursor.getString(5)



            var m = Model()
            m.id = id
            m.task_name = tasknm1
            m.task_description = taskdescription1
            m.task_date = taskdt1
            m.task_time = tasktime1
            m.task_priority = taskpriority1


            list.add(m)
        }
        return list
    }
    fun deletedata(id:Int)

    {
        var db = writableDatabase
        var deletequery = ID +" = " +id
        db.delete(TABLE_NAME,deletequery,null)

    }
    fun updatedata(m:Model)
    {
        var db = writableDatabase
        var values = ContentValues()
        values.put(ID,m.id)
        values.put(TASK_ADD,m.task_name)
        values.put(TASK_DESCRIPTION,m.task_description)
        values.put(TASK_DATE,m.task_date)
        values.put(TASK_TIME,m.task_time)
        values.put(TASK_PRIORITY,m.task_priority)

        var updatequery = ID+"="+m.id
        db.update(TABLE_NAME,values,updatequery,null)

    }



}
