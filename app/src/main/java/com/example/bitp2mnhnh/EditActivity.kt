package com.example.bitp2mnhnh

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {

    private val tag = "LIFECYCLE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        Log.d(tag, "EditActivity: onCreate()")

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtId = findViewById<EditText>(R.id.edtStudentId)
        val edtClass = findViewById<EditText>(R.id.edtClass)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)

        // Nhận dữ liệu hiện tại và điền sẵn vào các ô
        edtName.setText(intent.getStringExtra(MainActivity.KEY_NAME))
        edtId.setText(intent.getStringExtra(MainActivity.KEY_ID))
        edtClass.setText(intent.getStringExtra(MainActivity.KEY_CLASS))
        edtEmail.setText(intent.getStringExtra(MainActivity.KEY_EMAIL))
        edtPhone.setText(intent.getStringExtra(MainActivity.KEY_PHONE))

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            if (edtName.text.isBlank()) {
                edtName.error = "Vui lòng nhập họ tên"
                return@setOnClickListener
            }
            val resultIntent = Intent().apply {
                putExtra(MainActivity.KEY_NAME, edtName.text.toString().trim())
                putExtra(MainActivity.KEY_ID, edtId.text.toString().trim())
                putExtra(MainActivity.KEY_CLASS, edtClass.text.toString().trim())
                putExtra(MainActivity.KEY_EMAIL, edtEmail.text.toString().trim())
                putExtra(MainActivity.KEY_PHONE, edtPhone.text.toString().trim())
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        findViewById<Button>(R.id.btnCancel).setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    override fun onStart() { super.onStart(); Log.d(tag, "EditActivity: onStart()") }
    override fun onResume() { super.onResume(); Log.d(tag, "EditActivity: onResume()") }
    override fun onPause() { super.onPause(); Log.d(tag, "EditActivity: onPause()") }
    override fun onStop() { super.onStop(); Log.d(tag, "EditActivity: onStop()") }
    override fun onRestart() { super.onRestart(); Log.d(tag, "EditActivity: onRestart()") }
    override fun onDestroy() { super.onDestroy(); Log.d(tag, "EditActivity: onDestroy()") }
}