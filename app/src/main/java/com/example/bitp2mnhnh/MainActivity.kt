package com.example.bitp2mnhnh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMessage = findViewById(R.id.tvMessage)
        val edtInput = findViewById<EditText>(R.id.edtInput)
        val btnGoSecond = findViewById<Button>(R.id.btnGoSecond)

        btnGoSecond.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val bundle = Bundle()
            bundle.putString("KEY_TEXT", edtInput.text.toString())
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    // Nhận Intent quay lại từ màn hình 2
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        val bundle = intent.extras
        val reply = bundle?.getString("KEY_REPLY")
        if (reply != null) {
            tvMessage.text = reply
        }
    }
}