package com.example.m5_1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.VERSION

class DbHelper(context: Context) :SQLiteOpenHelper(context,DB_NAME,null,VERSION)
{
    companion object
    {
        var DB_NAME="MyData.db"
        var TABLE_NAME="Info"
        var VERSION=1
        var ID="Id"
        var NAME="Name"
        var NUMBER="NUmber"

    }
    override fun onCreate(p0: SQLiteDatabase?) {

        var query = " create table " + TABLE_NAME + " ( " + ID + " integer primary key, " + NAME + " text, " + NUMBER + " text " + " ) "
        p0!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        var upquery="DROP TABLE IF EXIST "+ TABLE_NAME
        p0!!.execSQL(upquery)
        onCreate(p0)
    }

    fun insertdata(m:Model):Long
    {
        var db=writableDatabase
        var values=ContentValues()
        values.put(NAME,m.name)
        values.put(NUMBER,m.number)
        var id=db.insert(TABLE_NAME, ID,values)
        return id
    }
    fun viewdata():ArrayList<Model>
    {
        var list:ArrayList<Model> = ArrayList()
        var db=readableDatabase
        var col= arrayOf(ID, NAME, NUMBER)
        var cursor: Cursor =db.query(TABLE_NAME,col,null,null,null,null,null,null)

        while (cursor.moveToNext())
        {
            var id=cursor.getInt(0)
            var name=cursor.getString(1)
            var num=cursor.getString(2)

            var m=Model()
            m.id=id
            m.name=name
            m.number=num

            list.add(m)
        }
        return list
    }

    fun deletedata(id:Int)
    {
        var db = writableDatabase
        var deletequery =  ID + " = "+id
        db.delete(TABLE_NAME,deletequery,null)
    }
    fun updatedata(m:Model)
    {
        var db = writableDatabase
        var values = ContentValues()
        values.put(ID,m.id)
        values.put(NAME,m.name)
        values.put(NUMBER,m.number)
        var upquery = ID+"="+m.id
        db.update(TABLE_NAME,values,upquery,null)

    }
}