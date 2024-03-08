package com.example.test

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,VERSION) {

    companion object{
        val DATABASE_NAME="Test"
        val VERSION=1
        val TABLE_NAME="COURSE"
        val COURSE_NAME="courseName"
        val COURSE_CREDIT="courseCredit"
        val COURSE_GRADE="courseGrade"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query="CREATE TABLE $TABLE_NAME(" +
                "$COURSE_NAME VARCHAR(50) PRIMARY KEY," +
                "$COURSE_CREDIT NUMBER," +
                "$COURSE_GRADE VARCHAR(2));"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query="DROP TABLE IF EXISTS $DATABASE_NAME"
        db?.execSQL(query)
        onCreate(db)
    }

    //insertData

    fun insertData(courseName:String,courseCredit:String,courseGrade:String):Boolean{
        val db=writableDatabase
        val values=ContentValues().apply {
            put(COURSE_NAME,courseName)
            put(COURSE_CREDIT,courseCredit)
            put(COURSE_GRADE,courseGrade)
        }
        val result=db.insert(TABLE_NAME,null,values)
        db.close()
        return result!=-1L
    }

    //get One Data
    fun getOneData(courseName:String):Cursor?{
        val db=readableDatabase
        val query="SELECT * FROM $TABLE_NAME WHERE $COURSE_NAME=$courseName"
        return db.rawQuery(query,null)
    }

    //get all data
    fun getAllData():Cursor?{
        val db=readableDatabase
        val query="SELECT * FROM $TABLE_NAME"
        return db.rawQuery(query,null)

    }

    //update Data
    fun updateData(courseName:String,courseCredit:String,courseGrade:String):Boolean{
        val db=writableDatabase
        val values=ContentValues().apply {
            put(COURSE_NAME,courseName)
            put(COURSE_GRADE,courseGrade)
            put(COURSE_CREDIT,courseCredit)
        }
        val result=db.update(TABLE_NAME,values,"$COURSE_NAME=?", arrayOf(courseName))
        return result>0
    }

    //delete data
    fun deleteData(courseName:String):Boolean{
        val db=writableDatabase
        val values=db.delete(TABLE_NAME,"$COURSE_NAME=?", arrayOf(courseName))
        db.close()
        return values>0
    }


}