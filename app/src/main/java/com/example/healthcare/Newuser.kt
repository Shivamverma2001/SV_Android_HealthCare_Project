package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Newuser : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var pass: EditText
    lateinit var email:EditText
    lateinit var confirmpass:EditText
    lateinit var button: Button
    lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newuser)
        name=findViewById(R.id.editTextRegUsername)
        pass=findViewById(R.id.editTextRegPassword)
        email=findViewById(R.id.editTextRegEmail)
        confirmpass=findViewById(R.id.editTextRegConfirmPassword)
        button=findViewById(R.id.buttonLogin)
        tv=findViewById(R.id.textViewExistingUser)
        val database = Database(this, "HealthCare", null, 1)
        button.setOnClickListener {
            if (name.text.toString().isEmpty() || pass.text.toString().isEmpty() || email.text.toString()
                    .isEmpty() || confirmpass.text.toString().length == 0) {
                Toast.makeText(this@Newuser, "Please fill all details", Toast.LENGTH_SHORT).show()
            } else {
                if (pass.text.toString().compareTo(confirmpass.text.toString()) != 0) {
                    Toast.makeText(this@Newuser, "Please match your passwords", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (isValid(pass.text.toString())) {
                        database.register(
                            name.text.toString(),
                            email.text.toString(),
                            pass.text.toString()
                        )
                        Toast.makeText(this@Newuser, "Record Inserted", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, Login::class.java)
                        startActivity(i)
                    } else {
                        Toast.makeText(
                            this@Newuser,
                            "Password must contain at least 8 character, having special letter, symbol and digit",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        tv.setOnClickListener {
            val i = Intent(this, Login::class.java)
            startActivity(i)
        }
    }
    fun isValid(pass: String): Boolean {
        var f1 = false
        var f2 = false
        var f3 = false

        if (pass.length < 8) {
            return false
        } else {
            for (p in 0 until pass.length) {
                if (pass[p].isLetter()) {
                    f1 = true
                }
            }

            for (r in 0 until pass.length) {
                if (pass[r].isDigit()) {
                    f2 = true
                }
            }

            for (s in 0 until pass.length) {
                val c = pass[s]
                if (c.toInt() in 33..47 || c == 64.toChar()) {
                    f3 = true
                }
            }

            if (f1 && f2 && f3) {
                return true
            }

            return false
        }
    }
}