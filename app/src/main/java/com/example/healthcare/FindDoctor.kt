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
    lateinit var dietician: CardView
    lateinit var dentist: CardView
    lateinit var surgeon: CardView
    lateinit var cardiologists: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_doctor)
        exit=findViewById(R.id.cardFDBack)
        familyPhysician=findViewById(R.id.cardFDFamilyPhysician)
        dietician=findViewById(R.id.cardFDDietician)
        dentist=findViewById(R.id.cardFDDentist)
        surgeon=findViewById(R.id.cardFDSurgeon)
        cardiologists=findViewById(R.id.cardFDCardiologists)
        val sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        exit.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedpreferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this@FindDoctor, Login::class.java))
        }
        familyPhysician.setOnClickListener{
            val i=Intent(this@FindDoctor, DoctorDetails::class.java)
            i.putExtra("title","Family Physician")
            startActivity(i)
        }
        dietician.setOnClickListener{
            val i=Intent(this@FindDoctor, DoctorDetails::class.java)
            i.putExtra("title","Dietician")
            startActivity(i)
        }
        dentist.setOnClickListener{
            val i=Intent(this@FindDoctor, DoctorDetails::class.java)
            i.putExtra("title","Dentist")
            startActivity(i)
        }
        surgeon.setOnClickListener{
            val i=Intent(this@FindDoctor, DoctorDetails::class.java)
            i.putExtra("title","Surgeon")
            startActivity(i)
        }
        cardiologists.setOnClickListener{
            val i=Intent(this@FindDoctor, DoctorDetails::class.java)
            i.putExtra("title","Cardiologist")
            startActivity(i)
        }
    }
}