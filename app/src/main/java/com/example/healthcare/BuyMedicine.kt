package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter

class BuyMedicine : AppCompatActivity() {
    lateinit var goTo: Button
    lateinit var back: Button
    lateinit var lv: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine)
        goTo=findViewById(R.id.buttonLTGoToCart)
        back=findViewById(R.id.buttonLDBack)
        lv=findViewById(R.id.listViewOD)
        val list: ArrayList<HashMap<String, String>> = ArrayList()
        val packages = arrayOf(
            arrayOf("Uprise Capsule","","","", "50"),
            arrayOf("Healthvit Chromium Picolinate Capsule", "", "", "", "305"),
            arrayOf("Vitamin B Complex Capsules", "","", "", "448"),
            arrayOf("Inlife Vitamin E Wheat Germ Oil Capsule", "","", "", "539"),
            arrayOf("Dolo Tablet", "","","", "30"),
            arrayOf("Crocin Advance Tablet", "","","", "50"),
            arrayOf("Strepsils Medicated Lozenges for Sore Throat", "","","", "58"),
            arrayOf("Tata ing Calcium Vitamin D","","","", "30"),
            arrayOf("Feronia -XT Tablet", "","","", "130")
        )
        val descriptions = listOf(
            "Besting and keeping the bones & teeth strongin\n"+
            "Reducing Fatigue/stress and muscular pains\n"+
            "Boosting immunity and increasing resistance against infection",
            "Chronium is an essential trace mineral that plays an important role in helping insulin reput",
            "Provides relief from vitamin B deficiencies\n"+
            "Helps in formation of red blood cells\n"+
            "Maintains healthy nervous system",
            "It promotes health as well as skin benefit. \n"+
            "It helps reduce skin blemish and pigmentation.\n" +
                    "It act as safeguard the skin from the harsh UVA and UVB sun rays.",
            "Dole 650 Tablet helps relieve pain and fever by blocking the release of certain chemical as",
            "Helps relieve fever and bring down a high temperature\n" +
                    "Suitable for people with a heart condition or high blood pressure",
            "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n"+
            "Provides a warm and conforting feeling during sore throat",
            "Reduces the risk of calcium deficiency, Rickets, and Osteoporosis\n" +
                    "Promotes mobility and flexibility of joints",
            "Helps to reduce the iron deficiency due to chronic blood loss or los intake of iron"
        )
        back.setOnClickListener{
            startActivity(Intent(this@BuyMedicine,Home::class.java))
        }
        for (i in 0 until packages.size) {
            val item = HashMap<String, String>()
            item["Line1"] = packages[i][0] // Change "line1" to "Line1"
            item["Line2"] = packages[i][1] // Change "line2" to "Line2"
            item["Line3"] = packages[i][2] // Change "l b7ine3" to "Line3"
            item["Line4"] = packages[i][3] // Change "line4" to "Line4"
            item["lines"] = "Cons Fees:" + packages[i][4] + "/-" // Change "line5" to "lines"
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
            val ii = Intent(this@BuyMedicine, BuyMedicineDetails::class.java)
            ii.putExtra("text1", packages[i][0])
            ii.putExtra("text2", descriptions[i])
            ii.putExtra("text3", packages[i][4])
            startActivity(ii)
        }
        goTo.setOnClickListener{
            startActivity(Intent(this@BuyMedicine,CartBuyMedicine::class.java))
        }
    }
}