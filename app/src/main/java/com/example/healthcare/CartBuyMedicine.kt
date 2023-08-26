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

class CartBuyMedicine : AppCompatActivity() {
    lateinit var datePickerDialog: DatePickerDialog
    lateinit var back: Button
    lateinit var checkout: Button
    lateinit var lv: ListView
    lateinit var date: Button
    lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_buy_medicine)
        tv=findViewById(R.id.textViewCartTotalCost)
        back=findViewById(R.id.buttonCartAppBack)
        checkout=findViewById(R.id.buttonCartAppRegister)
        date=findViewById(R.id.buttonCartAppDate)
        lv=findViewById(R.id.listViewOD)
        val sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val username = sharedpreferences.getString("username", "").toString()
        val database = Database(this, "HealthCare", null, 1)
        var totalAmount = 0f
        val dbData = database.getCartData(username, "medicine")
        Toast.makeText(this, dbData.toString(), Toast.LENGTH_LONG).show()

        val packages = Array(dbData.size) { arrayOfNulls<String>(5) }

        for (i in 0 until dbData.size) {
            val arrData = dbData[i].toString()
            val regex = """\bPackage (\d+): (\w+(?:\s+\w+)*) (\d+)""".toRegex()

            val matchResult = regex.find(arrData)
            if (matchResult != null && matchResult.groupValues.size >= 4) {
                val packageNumber = matchResult.groupValues[1]
                val packageName = matchResult.groupValues[2]
                val price = matchResult.groupValues[3].toFloat()

                packages[i] = arrayOf(packageNumber, packageName, "", "", price.toString())

                totalAmount += price
            } else {
                packages[i] = arrayOf("", arrData, "", "", "Cost: null/")
            }
        }

        val list: ArrayList<HashMap<String, String>> = ArrayList()
        for (i in 0 until packages.size) {
            val item = HashMap<String, String>()
            item["line1"] = packages[i]?.get(0) ?: ""
            item["line2"] = packages[i]?.get(1) ?: ""
            item["line3"] = packages[i]?.get(2) ?: ""
            item["line4"] = packages[i]?.get(3) ?: ""
            item["line5"] = packages[i]?.get(4) ?: ""
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
            startActivity(Intent(this,BuyMedicine::class.java))
        }
        date.setOnClickListener {
            initiatePicker()
        }
        checkout.setOnClickListener {
            val ii=Intent(this,BuyMedicineBook::class.java)
            ii.putExtra("price",totalAmount.toString())
            ii.putExtra("date",date.text)
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
}