package com.example.healthcare

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView

class Home : AppCompatActivity() {
    lateinit var exit:CardView
    lateinit var findDoctor:CardView
    lateinit var labTest:CardView
    lateinit var orderDetails:CardView
    lateinit var buyMedicine:CardView
    lateinit var healthArticle:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        exit=findViewById(R.id.cardExit)
        findDoctor=findViewById(R.id.cardFindDoctor)
        labTest=findViewById(R.id.cardLabText)
        orderDetails=findViewById(R.id.cardOrderDetails)
        buyMedicine=findViewById(R.id.cardBuyMedicine)
        healthArticle=findViewById(R.id.cardHealthDoctor)
        val sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val username = sharedpreferences.getString("username", "").toString()

        val exit: CardView = findViewById(R.id.cardExit)
        exit.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedpreferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this@Home, Login::class.java))
        }
        findDoctor.setOnClickListener{
            startActivity(Intent(this@Home, FindDoctor::class.java))
        }
        labTest.setOnClickListener{
            startActivity(Intent(this@Home, LabTest::class.java))
        }
        orderDetails.setOnClickListener{
            startActivity(Intent(this@Home, OrderDetails::class.java))
        }
        buyMedicine.setOnClickListener{
            startActivity(Intent(this,BuyMedicine::class.java))
        }
        healthArticle.setOnClickListener{
            startActivity(Intent(this,HealthArticle::class.java))
        }
    }
}