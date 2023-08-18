package com.example.healthcare

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(sqliteDatabase: SQLiteDatabase) {
        val qry1 = "CREATE TABLE users (username TEXT, email TEXT, password TEXT)"
        sqliteDatabase.execSQL(qry1)
    }

    override fun onUpgrade(sqliteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        // Handle database upgrade if needed
    }

    fun register(username: String, email: String, password: String) {
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("email", email)
        cv.put("password", password)

        val db = writableDatabase
        db.insert("users", null, cv)
        db.close()
    }
    fun login(username: String, password: String): Int {
        var result = 0
        val str = arrayOf(username, password)

        val db = readableDatabase
        val query = "SELECT * FROM users WHERE username=? AND password=?"
        val cursor = db.rawQuery(query, str)

        if (cursor.moveToFirst()) {
            result = 1
        }

        cursor.close()
        return result
    }
}
