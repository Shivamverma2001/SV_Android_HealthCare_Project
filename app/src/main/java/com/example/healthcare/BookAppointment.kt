package com.example.healthcare

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

class BookAppointment : AppCompatActivity() {
    lateinit var dateButton: Button
    lateinit var timeButton: Button
    lateinit var tv:TextView
    lateinit var name:EditText
    lateinit var add:EditText
    lateinit var number:EditText
    lateinit var fee:EditText
    lateinit var datePickerDialog: DatePickerDialog
    lateinit var timePickerDialog: TimePickerDialog
    lateinit var back:Button
    lateinit var book:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)
        tv=findViewById(R.id.textViewAppTitle)
        name=findViewById(R.id.editTextAppFullName)
        add=findViewById(R.id.editTextAppAddress)
        number=findViewById(R.id.editTextRegAppContactNumber)
        fee=findViewById(R.id.editTextRegAppFee)
        back=findViewById(R.id.buttonCartAppBack)
        book=findViewById(R.id.buttonCartAppRegister)
        val bundle: Bundle? = intent.extras
        val title = bundle?.get("text1")
        val fullname = bundle?.get("text2")
        val address = bundle?.get("text3")
        val contactnumber = bundle?.get("text4")
        val fees = bundle?.get("text5")
        name.keyListener=null
        add.keyListener=null
        number.keyListener=null
        fee.keyListener=null
        tv.text = title.toString()
        name.setText(fullname.toString())
        add.setText(address.toString())
        number.setText(contactnumber.toString())
        fee.setText(fees.toString())
        dateButton = findViewById(R.id.buttonCartAppDate)
        dateButton.setOnClickListener {
            initiatePicker()
        }
        timeButton = findViewById(R.id.buttonCartAppTime)
        timeButton.setOnClickListener {
            initiateTimePicker()
        }
        back.setOnClickListener{
            startActivity(Intent(this@BookAppointment,OrderDetails::class.java))
        }
        book.setOnClickListener {
            val sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            val username = sharedpreferences.getString("username", "").toString()
            val database = Database(this, "HealthCare", null, 1)

            val feeValue = fee.text.toString().toFloatOrNull()
            if (database.checkAppointmentExists(username, tv.text.toString() + " => " + name.text.toString(), add.text.toString(), number.text.toString(), dateButton.text.toString(), timeButton.text.toString()) == 1) {
                Toast.makeText(this, "Appointment already exists", Toast.LENGTH_SHORT).show()
            } else {
                if (feeValue != null) {
                    database.addOrder(username, tv.text.toString() + " => " + name.text.toString(), add.text.toString(), number.text.toString(), 0, dateButton.text.toString(), timeButton.text.toString(), feeValue, "appointment")
                }
                Toast.makeText(this, "Appointment is done successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Home::class.java))
            }
        }

    }
    private fun initiatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            dateButton.text = selectedDate
        }

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val style = AlertDialog.THEME_HOLO_DARK

        datePickerDialog = DatePickerDialog(this, style, dateSetListener, year, month, day)
        datePickerDialog.datePicker.minDate = cal.timeInMillis
        datePickerDialog.show()
    }
    private fun initiateTimePicker() {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hours, minutes ->
            // You can use the selected hours and minutes here
            val selectedTime = "$hours:$minutes"
            timeButton.text = selectedTime
        }

        val cal = Calendar.getInstance()
        val hours = cal.get(Calendar.HOUR_OF_DAY)
        val minutes = cal.get(Calendar.MINUTE)
        val style = AlertDialog.THEME_HOLO_DARK

        timePickerDialog = TimePickerDialog(this, style, timeSetListener, hours, minutes, true)
        timePickerDialog.show()
    }

}