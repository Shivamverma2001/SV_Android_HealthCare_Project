package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter

class LabTest : AppCompatActivity() {
    lateinit var goTo:Button
    lateinit var back:Button
    lateinit var lv:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test)
        goTo=findViewById(R.id.buttonLTGoToCart)
        back=findViewById(R.id.buttonLDBack)
        lv=findViewById(R.id.listViewOD)
        val list: ArrayList<HashMap<String, String>> = ArrayList()
        val packages = arrayOf(
            arrayOf("Package 1: Full Body Checkup","","","","999"),
            arrayOf("Package 2: Blood Glucose Fasting","","","","299"),
            arrayOf("Package 3: COVID Antibody IgG","","","","899"),
            arrayOf("Package 4: Thyroid Check","","","","499"),
            arrayOf("Package 5: Immunity Check","","","","699")
        )
        val packageDetails = arrayOf(
            "Blood Glucose Fasting\n"+
            "Complete Hemogram\n"+
            "HbA1c\n"+
            "Iron Studies\n"+
            "Kidney Function Test\n"+
            "LOH Lactate Dehydrogenase, Serum\n"+
            "Lipid Profile\n"+
            "Liver Function Test",
            "COVID-19 Antibody - IgG",
            "Thyroid Profile-Total (T3, T4 TSH Ultra-sensitive)",
            "complete Hemogram\n"+
            "CRP (C Reactive Protein) Quantitative, Serum\n"+
            "Iron Studies\n"+
                    "Kidney Function Test\n"+
            "Vitamin D Total-25 Hydroxy\n"+
            "Liver Function Test\n"+
            "Lipid Profile",
            "Kidney Function Test\n"+
                    "Vitamin D Total-25 Hydroxy\n"+
                    "Liver Function Test\n"+
                    "Lipid Profile",
        )
        back.setOnClickListener{
            startActivity(Intent(this@LabTest,Home::class.java))
        }
        for (i in 0 until packages.size) {
            val item = HashMap<String, String>()
            item["Line1"] = packages[i][0] // Change "line1" to "Line1"
            item["Line2"] = packages[i][1] // Change "line2" to "Line2"
            item["Line3"] = packages[i][2] // Change "line3" to "Line3"
            item["Line4"] = packages[i][3] // Change "line4" to "Line4"
            item["lines"] = "Total Cost:" + packages[i][4] + "/-" // Change "line5" to "lines"
            list.add(item)
        }
        val simpleAdapter = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("Line1", "Line2", "Line3", "Line4", "lines"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )
        lv.adapter = simpleAdapter
        lv.setOnItemClickListener{ parent, view, position, id ->
            val i = position // Get the clicked item position
            val ii = Intent(this@LabTest, BookAppointmentDetails::class.java)
            ii.putExtra("text1", packages[i][0])
            ii.putExtra("text2", packageDetails[i])
            ii.putExtra("text3", packages[i][4])
            startActivity(ii)
        }
        goTo.setOnClickListener{
            startActivity(Intent(this@LabTest,CardLab::class.java))
        }
    }
}