package com.example.healthcare

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class OrderDetails : AppCompatActivity() {
    lateinit var lv:ListView
    lateinit var back:Button
    lateinit var clear:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        lv=findViewById(R.id.listViewOD)
        back=findViewById(R.id.buttonODBack)
        clear=findViewById(R.id.buttonODClear)
        back.setOnClickListener {
            startActivity(Intent(this,Home::class.java))
        }
        val list: ArrayList<HashMap<String, String>> = ArrayList()
        val sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val username = sharedpreferences.getString("username", "").toString()
        val database = Database(this, "HealthCare", null, 1)
        val dbData =database.getOrderData(username)
        val orderDetails = Array(dbData.size) { arrayOfNulls<String>(5) }
        for (i in 0 until dbData.size) {
            orderDetails[i] = arrayOfNulls(5)

            val arrData = dbData[i].toString() // Use dbData[i] instead of dbData[1]
            val strData = arrData.split(Regex("\\$"))

            orderDetails[i][0] = strData[0]
            orderDetails[i][1] = strData[1]

            if (strData[7] == "medicine") {
                orderDetails[i][3] = "Del:${strData[4]}"
            } else {
                orderDetails[i][3] = "Date:${strData[4]}"+" " +"\nTime:${strData[5]}"
            }
            orderDetails[i][2] = "Rs.${strData[6]}"
            orderDetails[i][4] = strData[7]
        }
        for (i in 0 until orderDetails.size) {
            val item = HashMap<String, String>()
            item["Line1"] = orderDetails[i][0]?:" " // Change "line1" to "Line1"
            item["Line2"] = orderDetails[i][1]?:" " // Change "line2" to "Line2"
            item["Line3"] = orderDetails[i][2]?:" " // Change "line3" to "Line3"
            item["Line4"] = orderDetails[i][3]?:" " // Change "line4" to "Line4"
            item["lines"] = orderDetails[i][4]?:" "// Change "line5" to "lines"
            list.add(item)
        }
        val simpleAdapter = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("Line1", "Line2", "Line3", "Line4", "lines"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )
        val lst: ListView = findViewById(R.id.listViewOD)
        lst.adapter = simpleAdapter
        lst.setOnItemClickListener { parent, view, position, id ->
            val i = position // Get the clicked item position
            val ii = Intent(this@OrderDetails, BookAppointment::class.java)
            ii.putExtra("text1", title.toString())
            ii.putExtra("text2", orderDetails[i][0])
            ii.putExtra("text3", orderDetails[i][1])
            ii.putExtra("text4", orderDetails[i][3])
            ii.putExtra("text5", orderDetails[i][4])
            startActivity(ii)
        }
        clear.setOnClickListener {
            val database = Database(this, "HealthCare", null, 1)
            database.remove()
            startActivity(Intent(this@OrderDetails, OrderDetails::class.java))
        }
    }
}