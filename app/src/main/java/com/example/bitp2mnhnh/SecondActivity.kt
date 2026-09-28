package com.example.bitp2mnhnh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tvReceived = findViewById<TextView>(R.id.tvReceived)
        val btnGoMain = findViewById<Button>(R.id.btnGoMain)

        val text = intent.getStringExtra("KEY_TEXT") ?: ""
        tvReceived.text = "Nhận từ màn hình 1: $text"

        btnGoMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("KEY_REPLY", "Đã quay về từ màn hình 2")
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}