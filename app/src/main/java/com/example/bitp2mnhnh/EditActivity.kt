package com.example.bitp2mnhnh

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val edtName = findViewById<EditText>(R.id.edtName)

        // Nhận tên hiện tại từ Intent và điền sẵn vào EditText
        edtName.setText(intent.getStringExtra("KEY_NAME"))

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val resultIntent = Intent().putExtra("KEY_NAME", edtName.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}