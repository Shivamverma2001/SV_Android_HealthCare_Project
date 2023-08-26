package com.example.healthcare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LabTestBook : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var pincode: EditText
    lateinit var contactNumber: EditText
    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test_book)
        name=findViewById(R.id.editTextLTDUsername)
        email=findViewById(R.id.editTextLTDEmail)
        pincode=findViewById(R.id.editTextLTDPincode)
        contactNumber=findViewById(R.id.editTextLTDNumber)
        btn=findViewById(R.id.buttonLTDBook)
        val intent = getIntent()

        val price = intent.getStringExtra("price")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        btn.setOnClickListener {
            val sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            val username = sharedpreferences.getString("username", "").toString()
            val database = Database(this, "HealthCare", null, 1)
            database.addOrder(username, name.text.toString(), email.text.toString(), contactNumber.text.toString(), pincode.text.toString().toInt(), date.toString(), time.toString(), price.toString().toFloat(), "lab")
            database.removeCart(username,"lab")
            Toast.makeText(this,"Your booking is done successfully",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,Home::class.java))
        }
    }
}