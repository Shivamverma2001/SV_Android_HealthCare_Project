package com.example.healthcare

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

class CardLab : AppCompatActivity() {
    lateinit var datePickerDialog: DatePickerDialog
    lateinit var timePickerDialog: TimePickerDialog
    lateinit var back: Button
    lateinit var checkout: Button
    lateinit var lv:ListView
    lateinit var date: Button
    lateinit var time: Button
    lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_lab)
        tv=findViewById(R.id.textViewCartTotalCost)
        back=findViewById(R.id.buttonCartAppBack)
        checkout=findViewById(R.id.buttonCartAppRegister)
        date=findViewById(R.id.buttonCartAppDate)
        time=findViewById(R.id.buttonCartAppTime)
        lv=findViewById(R.id.listViewOD)
        val sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val username = sharedpreferences.getString("username", "").toString()
        val database = Database(this, "HealthCare", null, 1)
        var totalAmount = 0f
        val dbData = database.getCartData(username, "lab")

//        val packages = Array(dbData.size) { arrayOfNulls<String>(5) }
        val packages = mutableListOf<Array<String?>>()
        for ((product, price) in dbData) {
            val regex = """\bPackage (\d+): (\w+(?:\s+\w+)*) (\d+)""".toRegex()
            val matchResult = regex.find(product)
            if (matchResult != null && matchResult.groupValues.size >= 4) {
                val packageNumber = matchResult.groupValues[1]
                val packageName = matchResult.groupValues[2]
                val floatValue = price.toFloat()

                packages.add(arrayOf(packageNumber, packageName, "", "", floatValue.toString()))
                totalAmount += floatValue
            } else {
                // Handle null price here
                val priceText = price ?: "Cost: null/"
                packages.add(arrayOf("", product, "", "","Cost: "+ priceText))
                totalAmount +=priceText.toString().toInt()
            }
        }

        val list: ArrayList<HashMap<String, String>> = ArrayList()
        for (pkg in packages) {
            val item = HashMap<String, String>()
            item["line1"] = pkg[0] ?: ""
            item["line2"] = pkg[1] ?: ""
            item["line3"] = pkg[2] ?: ""
            item["line4"] = pkg[3] ?: ""
            item["line5"] = pkg[4] ?: ""
            list.add(item)
        }

        val sa = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )
        lv.adapter = sa
        tv.text = "Total: $totalAmount"
        back.setOnClickListener {
            startActivity(Intent(this,LabTest::class.java))
        }
        date.setOnClickListener {
            initiatePicker()
        }
        time.setOnClickListener {
            initiateTimePicker()
        }
        checkout.setOnClickListener {
            val ii=Intent(this,LabTestBook::class.java)
            ii.putExtra("price",totalAmount.toString())
            ii.putExtra("date",date.text)
            ii.putExtra("time",time.text)
            startActivity(ii)
        }
    }
    private fun initiatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            date.text = selectedDate
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
            time.text = selectedTime
        }

        val cal = Calendar.getInstance()
        val hours = cal.get(Calendar.HOUR_OF_DAY)
        val minutes = cal.get(Calendar.MINUTE)
        val style = AlertDialog.THEME_HOLO_DARK

        timePickerDialog = TimePickerDialog(this, style, timeSetListener, hours, minutes, true)
        timePickerDialog.show()
    }
}