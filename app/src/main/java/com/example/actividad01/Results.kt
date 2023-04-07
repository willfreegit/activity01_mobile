package com.example.actividad01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_results.*

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        result.setText(intent.getStringExtra("result"))

        back.setOnClickListener {
            back()
        }
    }

    fun back(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}