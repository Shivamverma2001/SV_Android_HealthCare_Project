package com.example.healthcare

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class BookAppointmentDetails : AppCompatActivity() {
    lateinit var tvPackageName:TextView
    lateinit var tvTotalCost:TextView
    lateinit var edDetails:EditText
    lateinit var btnToCart:Button
    lateinit var back:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment_details)
        tvPackageName=findViewById(R.id.textPackageLD)
        tvTotalCost=findViewById(R.id.textViewLDTotalCost)
        btnToCart=findViewById(R.id.buttonLDGoToCart)
        back=findViewById(R.id.buttonLDBack)
        edDetails=findViewById(R.id.teditTextLDMutli)
        val bundle: Bundle? = intent.extras
        tvPackageName.setText(bundle?.get("text1").toString())
        edDetails.setText(bundle?.get("text2").toString())
        tvTotalCost.setText(bundle?.get("text3").toString())
        back.setOnClickListener{
            startActivity(Intent(this@BookAppointmentDetails,LabTest::class.java))
        }
    }
}