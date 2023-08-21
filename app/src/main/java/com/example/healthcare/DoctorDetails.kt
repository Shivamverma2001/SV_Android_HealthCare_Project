package com.example.healthcare

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//159
class DoctorDetails : AppCompatActivity() {
    val doctor_Details1: Array<Array<String>> = arrayOf(

        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: Byrs", "Mobile No:8898989898", "300"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No: Pres","500"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Katrai", "Exp: 7yrs", "Mobile No:7798989898", "800"),
        arrayOf("Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: Syrs", "Mobile No:989898v", "600"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989998","900")
    )
    val doctor_Details2: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No: Pres","500"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Katrai", "Exp: 7yrs", "Mobile No:7798989898", "800"),
        arrayOf("Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: Syrs", "Mobile No:989898v", "600"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989998","900"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: Byrs", "Mobile No:8898989898", "300")
    )
    val doctor_Details3: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: Syrs", "Mobile No:989898v", "600"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989998","900"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: Byrs", "Mobile No:8898989898", "300"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No: Pres","500"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Katrai", "Exp: 7yrs", "Mobile No:7798989898", "800")
    )
    val doctor_Details4: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989998","900"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: Byrs", "Mobile No:8898989898", "300"),
        arrayOf("Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: Syrs", "Mobile No:989898v", "600"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No: Pres","500"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Katrai", "Exp: 7yrs", "Mobile No:7798989898", "800")
    )
    val doctor_Details5: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989998","900"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: Byrs", "Mobile No:8898989898", "300"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No: Pres","500"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Katrai", "Exp: 7yrs", "Mobile No:7798989898", "800"),
        arrayOf("Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: Syrs", "Mobile No:989898v", "600")
    )
    var doctor_Details: Array<Array<String>> = arrayOf()
    val list: ArrayList<HashMap<String, String>> = ArrayList()
    lateinit var tv:TextView
    lateinit var back:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)
        tv=findViewById(R.id.textViewDDTitle)
        back=findViewById(R.id.buttonDDBack)
        val bundle: Bundle? = intent.extras
        val title = bundle?.get("title")
        tv.text= title.toString()
        if(title.toString().compareTo("Family Physician")==0){
            doctor_Details=doctor_Details1
        }else if(title.toString().compareTo("Dietician")==0){
            doctor_Details=doctor_Details2
        }else if(title.toString().compareTo("Dentist")==0){
            doctor_Details=doctor_Details3
        }else if(title.toString().compareTo("Surgeon")==0){
            doctor_Details=doctor_Details4
        }else{
            doctor_Details=doctor_Details5
        }
        back.setOnClickListener{
            val ii=Intent(this@DoctorDetails, FindDoctor::class.java)
            startActivity(ii)
        }
        // Inside your onCreate function
        for (i in 0 until doctor_Details.size) {
            val item = HashMap<String, String>()
            item["Line1"] = doctor_Details[i][0] // Change "line1" to "Line1"
            item["Line2"] = doctor_Details[i][1] // Change "line2" to "Line2"
            item["Line3"] = doctor_Details[i][2] // Change "line3" to "Line3"
            item["Line4"] = doctor_Details[i][3] // Change "line4" to "Line4"
            item["lines"] = "Cons Fees:" + doctor_Details[i][4] + "/-" // Change "line5" to "lines"
            list.add(item)
        }
        val simpleAdapter = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("Line1", "Line2", "Line3", "Line4", "lines"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )
        val lst: ListView = findViewById(R.id.listViewDD)
        lst.adapter = simpleAdapter
        lst.setOnItemClickListener { parent, view, position, id ->
            val i = position // Get the clicked item position
            val ii = Intent(this@DoctorDetails, BookAppointment::class.java)
            ii.putExtra("text1", title.toString())
            ii.putExtra("text2", doctor_Details[i][0])
            ii.putExtra("text3", doctor_Details[i][1])
            ii.putExtra("text4", doctor_Details[i][3])
            ii.putExtra("text5", doctor_Details[i][4])
            startActivity(ii)
        }
    }
}