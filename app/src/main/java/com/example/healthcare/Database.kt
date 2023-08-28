package com.example.healthcare

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class Database(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(sqliteDatabase: SQLiteDatabase) {
        val qry1 = "CREATE TABLE users (username TEXT, email TEXT, password TEXT)"
        sqliteDatabase.execSQL(qry1)
        val qry2 = "CREATE TABLE cart (username TEXT, product TEXT,price FLOAT, otype FLOAT)"
        sqliteDatabase.execSQL(qry2)
        val qry3 = "CREATE TABLE orderplace (username TEXT, fullname TEXT, address TEXT, contactnumber TEXT, pincode INT, date TEXT, time TEXT, amount FLOAT, otype TEXT)"
        sqliteDatabase.execSQL(qry3)
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
    fun addCart(username: String, product: String, price: Float, otype: String) {
        val cv = ContentValues()

        cv.put("username", username)
        cv.put("product", product)
        cv.put("price", price)
        cv.put("otype", otype)

        val db = writableDatabase

        db.insert("cart", null, cv)

        db.close()
    }
    fun checkCart(username: String, product: String): Int {
        var result = 0
        val str = arrayOf(username, product)

        val db = readableDatabase

        val c = db.rawQuery("SELECT * FROM cart WHERE username = ? AND product = ?", str)

        if (c.moveToFirst()) {
            result = 1
        }

        c.close()
        db.close()

        return result
    }
    fun removeCart(username: String, otype: String) {
        val str = arrayOf(username, otype)

        val db = writableDatabase

        db.delete("cart", "username=? and otype=?", str)

        db.close()
    }
    fun getCartDataPackage(username: String, otype: String): ArrayList<String> {
        val arr = ArrayList<String>()
        val db = readableDatabase
        val str = arrayOfNulls<String>(2)

        str[0] = username
        str[1] = otype

        val c = db.rawQuery("SELECT * FROM cart WHERE username = ? AND otype = ?", str)

        if (c.moveToFirst()) {
            do {
                val product = c.getString(1)
                val price = c.getString(2)
                arr.add("$product $price")
            } while (c.moveToNext())
        }

        c.close()
        db.close()

        return arr
    }
    fun getCartData(username: String, otype: String): HashMap<String, String> {
    val cartData = HashMap<String, String>()
    val db = readableDatabase
    val str = arrayOf(username, otype)

    val c = db.rawQuery("SELECT * FROM cart WHERE username = ? AND otype = ?", str)

    if (c.moveToFirst()) {
        do {
            val product = c.getString(1)
            val price = c.getString(2)
            cartData[product] = price
        } while (c.moveToNext())
    }

    c.close()
    db.close()

    return cartData
}

    fun addOrder(username: String, fullname: String, address: String, contact: String, pincode: Int, date: String, time: String, price: Float, otype: String) {
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("fullname", fullname)
        cv.put("address", address)
        cv.put("contactnumber", contact)
        cv.put("pincode", pincode)
        cv.put("date", date)
        cv.put("time", time)
        cv.put("amount", price)
        cv.put("otype", otype)
        val db = writableDatabase
        db.insert("orderplace", null, cv)
        db.close()
    }
    fun checkAppointmentExists(username: String, fullname: String, address: String, contact: String, date: String, time: String): Int {
        var result = 0

        val str = arrayOfNulls<String>(6)
        str[0] = username
        str[1] = fullname
        str[2] = address
        str[3] = contact
        str[4] = date
        str[5] = time
        val db = readableDatabase
        val sql = "SELECT * FROM orderplace WHERE username = ? AND fullname = ? AND address = ? AND contactnumber = ? AND date = ? AND time = ?"
        val selectionArgs = arrayOf(username, fullname, address, contact, date, time)
        val c = db.rawQuery(sql, selectionArgs)
        if (c.moveToFirst()) {
            result = 1
        }
        db.close()
        return result
    }

    fun getOrderData(username: String): ArrayList<String> {
        val arr = ArrayList<String>()

        val db = readableDatabase
        val str = arrayOf(username)

        val query = "SELECT * FROM orderplace WHERE username = ?"
        val c = db.rawQuery(query, str)

        if (c.moveToFirst()) {
            do {
                arr.add("${c.getString(1)}$${c.getString(2)}$${c.getString(3)}$${c.getString(4)}$${c.getString(5)}$${c.getString(6)}$${c.getString(7)}$${c.getString(8)}")
            } while (c.moveToNext())
        }
        c.close()
        db.close()
        return arr
    }
    fun remove(){
        val db=readableDatabase
        val query1="DELETE FROM orderplace"
        val query2="DELETE FROM cart"
        db.execSQL(query1)
        db.execSQL(query2)
        db.close()
    }
}
