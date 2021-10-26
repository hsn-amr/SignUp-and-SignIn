package com.example.signupandsignin

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context): SQLiteOpenHelper(context,"User.db",null,1) {

    val sqliteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE user_info(id INTEGER PRIMARY KEY, name TEXT, location TEXT," +
                "phone INTEGER, password TEXT)")
        p0!!.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addNewUser(user: User):Long{
        val cv = ContentValues()
        cv.put("name",user.username)
        cv.put("location",user.location)
        cv.put("phone",user.phone)
        cv.put("password",user.password)
        return sqliteDatabase.insert("user_info",null,cv)
    }

    @SuppressLint("Range")
    fun getUserByUsername(username: String): User? {
        var user: User? = null
        val cursor:Cursor = sqliteDatabase.query("user_info",null,"name=?", arrayOf(username),null,null,null)
        if(cursor.moveToFirst()){
            val pk = cursor.getInt(cursor.getColumnIndex("id"))
            val username = cursor.getString(cursor.getColumnIndex("name"))
            val location = cursor.getString(cursor.getColumnIndex("location"))
            val phone = cursor.getInt(cursor.getColumnIndex("phone"))
            val password = cursor.getString(cursor.getColumnIndex("password"))
            user = User(pk,username,location,phone,password)
        }
        return user
    }

}