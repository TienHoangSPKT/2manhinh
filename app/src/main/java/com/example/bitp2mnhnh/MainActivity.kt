package com.example.bitp2mnhnh

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvProfileName: TextView
    private var currentName = "Chưa có thông tin"

    // Bước 1: đăng ký Launcher ở mức thuộc tính class
    private val editLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Bước 4: nhận kết quả trả về
        if (result.resultCode == Activity.RESULT_OK) {
            val newName = result.data?.getStringExtra("KEY_NAME") ?: ""
            if (newName.isNotBlank()) {
                currentName = newName
                tvProfileName.text = "Họ tên: $currentName"
                Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvProfileName = findViewById(R.id.tvProfileName)

        // Khôi phục khi xoay màn hình
        currentName = savedInstanceState?.getString("SAVED_NAME") ?: currentName
        tvProfileName.text = "Họ tên: $currentName"

        // Bước 2: bấm nút -> gửi tên hiện tại và mở EditActivity
        findViewById<Button>(R.id.btnEdit).setOnClickListener {
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra("KEY_NAME", currentName)
            }
            editLauncher.launch(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("SAVED_NAME", currentName)
    }
}