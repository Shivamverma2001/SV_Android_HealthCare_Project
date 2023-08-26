package com.example.healthcare

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HealthArticleDetails : AppCompatActivity() {
    lateinit var tv:TextView
    lateinit var img:ImageView
    lateinit var back:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_article_details)
        tv=findViewById(R.id.textViewDDTitle2)
        img=findViewById(R.id.imageView)
        back=findViewById(R.id.buttonLDBack)
        back.setOnClickListener {
            startActivity(Intent(this,HealthArticle::class.java))
        }
        val bundle: Bundle? = intent.extras
        tv.text= bundle?.get("text1").toString()
        val id = bundle?.get("text2") as? Int
        id?.let {
            val bitmap = BitmapFactory.decodeResource(resources, it)
            img.setImageBitmap(bitmap)
        }
    }
}