package com.example.konamoniruzzaman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonAction()
    }
    private fun buttonAction() {
        btnAddWord.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateActivity::class.java)
            startActivity(intent);
        }

        btnViewWord.setOnClickListener {
            val intent = Intent(this@MainActivity, ViewWord::class.java)
            startActivity(intent);
        }
    }
}