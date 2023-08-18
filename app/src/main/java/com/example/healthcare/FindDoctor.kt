package com.example.healthcare

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
//143
class FindDoctor : AppCompatActivity() {
    lateinit var exit: CardView
    lateinit var familyPhysician: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_doctor)
        exit=findViewById(R.id.cardFDBack)
        familyPhysician=findViewById(R.id.cardFDFamilyPhysician)
        val sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val username = sharedpreferences.getString("username", "31").toString()
        exit.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedpreferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this@FindDoctor, Login::class.java))
        }
        familyPhysician.setOnClickListener{
            startActivity(Intent(this@FindDoctor, DoctorDetails::class.java))
        }
    }
}