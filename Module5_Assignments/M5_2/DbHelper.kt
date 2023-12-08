package com.example.stickynotes_m5_a2


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.m5_2.Model


class DbHelper(context: Context) : SQLiteOpenHelper (context, DB_NAME, null, DB_VERSION) {

    companion object {
        var DB_NAME = "addnote.db"
        var TABLE_NAME = "stikcy_note"

        var ID = "id"
        var TITLE = "title"
        var TAKE_NOTE = "note"
        var DB_VERSION = 3


    }


    override fun onCreate(p0: SQLiteDatabase?) {
        var query =
            "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT," + TAKE_NOTE + " TEXT" + ")"
        p0!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        var upquery = "DROP TABLE if exists " + TABLE_NAME
        p0!!.execSQL(upquery)
        onCreate(p0)
    }


    fun insertdata(m: Model): Long {
        var db = writableDatabase // you have to write something for data insertion

        var values = ContentValues()//add all data inside values

        values.put(TITLE, m.title)
        values.put(TAKE_NOTE, m.note)


        var id = db.insert(TABLE_NAME, ID, values)

        return id
    }

    fun viewdata(): MutableList<Model> {

        var db = readableDatabase
        var list: ArrayList<Model> = ArrayList<Model>()
        var col = arrayOf(ID, TITLE, TAKE_NOTE)
        var cursor: Cursor = db.query(TABLE_NAME, col, null, null, null, null, null, null)
        //select * from tablename

        while (cursor.moveToNext()) {
            var id = cursor.getInt(0)
            var title1 = cursor.getString(1)
            var note1 = cursor.getString(2)


            var m = Model()
            m.id = id
            m.title = title1
            m.note = note1


            list.add(m)
        }
        return list
    }

    fun deleetdata(id:Int)

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
        values.put(TITLE,m.title)
        values.put(TAKE_NOTE,m.note)

        var updatequery = ID+"="+m.id
        db.update(TABLE_NAME,values,updatequery,null)

    }



}
