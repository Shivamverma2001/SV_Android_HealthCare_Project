package com.example.healthcare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
//1.15
class Login : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var pass:EditText
    lateinit var button:Button
    lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        name=findViewById(R.id.editTextLoginUsername)
        pass=findViewById(R.id.editTextLoginPassword)
        button=findViewById(R.id.buttonLogin)
        tv=findViewById(R.id.textViewNewUser)
        val database = Database(this, "HealthCare", null, 1)
        button.setOnClickListener {
            if (name.text.toString().isEmpty() || pass.text.toString().isEmpty()) {
                Toast.makeText(this@Login, "Please fill all details", Toast.LENGTH_SHORT).show()
            } else {
                if (database.login(name.text.toString(), pass.text.toString()) == 1) {
                    Toast.makeText(this@Login, "Login sucessfully", Toast.LENGTH_SHORT).show()
                    val sharedPrefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
                    val editor = sharedPrefs.edit()
                    editor.putString("username", name.text.toString())
                    // To save our data with key and value.
                    editor.apply()
                    val i = Intent(this, Home::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(this@Login, "Invalid username and password", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        tv.setOnClickListener {
            val i = Intent(this, Newuser::class.java)
            startActivity(i)
        }
    }
}