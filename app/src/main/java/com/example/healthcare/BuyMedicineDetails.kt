package com.example.healthcare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class BuyMedicineDetails : AppCompatActivity() {
    lateinit var tvPackageName: TextView
    lateinit var tvTotalCost: TextView
    lateinit var edDetails: EditText
    lateinit var btnToCart: Button
    lateinit var back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine_details)
        tvPackageName=findViewById(R.id.textPackageCart)
        tvTotalCost=findViewById(R.id.textViewCartTotalCost)
        btnToCart=findViewById(R.id.buttonLDGoToCart)
        back=findViewById(R.id.buttonLDBack)
        edDetails=findViewById(R.id.editTextCart)
        edDetails.keyListener=null
        val bundle: Bundle? = intent.extras
        tvPackageName.setText(bundle?.get("text1").toString())
        edDetails.setText(bundle?.get("text2").toString())
        tvTotalCost.setText(bundle?.get("text3").toString())
        back.setOnClickListener{
            startActivity(Intent(this@BuyMedicineDetails,BuyMedicine::class.java))
        }
        btnToCart.setOnClickListener{
            val sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            val username = sharedpreferences.getString("username", "").toString()
            val product=tvPackageName.text.toString()
            val price=bundle?.get("text3").toString().toFloat()
            val database = Database(this, "HealthCare", null, 1)
            if(database.checkCart(username,product)==1){
                Toast.makeText(this,"Aready in the cart", Toast.LENGTH_SHORT).show()
            }else{
                database.addCart(username,product,price,"medicine")
                Toast.makeText(this,"Record inserted successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@BuyMedicineDetails,BuyMedicine::class.java))
            }
        }
    }
}