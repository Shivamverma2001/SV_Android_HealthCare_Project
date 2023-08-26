package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter

class HealthArticle : AppCompatActivity() {
    lateinit var lv:ListView
    lateinit var back:Button
    val packages: Array<Array<String>> = arrayOf(
        arrayOf("Walking Daily","","","", "Click More Details"),
        arrayOf("Home Care of COVID 19","","","", "Click More Details"),
        arrayOf("Stop Smoking","","","", "Click More Details"),
        arrayOf("Menstrual Cramps","","","", "Click More Details"),
        arrayOf("Healthy Gut","","","", "Click More Details")
    )
    private val images: IntArray = intArrayOf(
        R.drawable.health1,
        R.drawable.health2,
        R.drawable.health3,
        R.drawable.health4,
        R.drawable.health5,
    )
    val bookand: String = "buy dione details."
    val item: HashMap<String, String> = HashMap()
    val doctorList: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_article)
        lv=findViewById(R.id.listViewOD)
        back=findViewById(R.id.buttonODBack)
        back.setOnClickListener {
            startActivity(Intent(this,Home::class.java))
        }
        val list: ArrayList<HashMap<String, String>> = ArrayList()
        for (i in 0 until packages.size) {
            val item = HashMap<String, String>()
            for (j in 0 until 5) {
                item["line${j + 1}"] = packages[i]?.get(j) ?: ""
            }
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

        lv.setOnItemClickListener { parent, view, position, id ->
            if (position in 0 until packages.size) {
                val i = position
                val ii = Intent(this@HealthArticle, HealthArticleDetails::class.java)
                ii.putExtra("text1", packages[i]?.get(0))
                ii.putExtra("text2", images[i]) // Assuming images is a valid array
                startActivity(ii)
            }
        }

    }
}