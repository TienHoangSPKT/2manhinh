package com.example.bitp2mnhnh

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_NAME = "KEY_NAME"
        const val KEY_ID = "KEY_ID"
        const val KEY_CLASS = "KEY_CLASS"
        const val KEY_EMAIL = "KEY_EMAIL"
        const val KEY_PHONE = "KEY_PHONE"
        const val KEY_AVATAR = "KEY_AVATAR"
        const val TAG = "LIFECYCLE"
    }

    private lateinit var imgAvatar: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvStudentId: TextView
    private lateinit var tvClass: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView

    private var name = ""
    private var studentId = ""
    private var className = ""
    private var email = ""
    private var phone = ""
    private var avatarUri: Uri? = null

    // Nhận kết quả từ EditActivity
    private val editLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            name = data?.getStringExtra(KEY_NAME) ?: name
            studentId = data?.getStringExtra(KEY_ID) ?: studentId
            className = data?.getStringExtra(KEY_CLASS) ?: className
            email = data?.getStringExtra(KEY_EMAIL) ?: email
            phone = data?.getStringExtra(KEY_PHONE) ?: phone
            showProfile()
            Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show()
        }
    }

    // Chọn ảnh đại diện từ thư viện
    private val avatarLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            avatarUri = uri
            imgAvatar.setImageURI(uri)
            Toast.makeText(this, "Đã chọn ảnh đại diện mới!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Người dùng đã hủy chọn ảnh", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "MainActivity: onCreate()")

        imgAvatar = findViewById(R.id.imgAvatar)
        tvName = findViewById(R.id.tvName)
        tvStudentId = findViewById(R.id.tvStudentId)
        tvClass = findViewById(R.id.tvClass)
        tvEmail = findViewById(R.id.tvEmail)
        tvPhone = findViewById(R.id.tvPhone)

        // Khôi phục dữ liệu khi xoay màn hình
        savedInstanceState?.let {
            name = it.getString(KEY_NAME, "")
            studentId = it.getString(KEY_ID, "")
            className = it.getString(KEY_CLASS, "")
            email = it.getString(KEY_EMAIL, "")
            phone = it.getString(KEY_PHONE, "")
            it.getString(KEY_AVATAR)?.let { s ->
                avatarUri = Uri.parse(s)
                imgAvatar.setImageURI(avatarUri)
            }
        }
        showProfile()

        imgAvatar.setOnClickListener { avatarLauncher.launch("image/*") }

        findViewById<Button>(R.id.btnEdit).setOnClickListener {
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra(KEY_NAME, name)
                putExtra(KEY_ID, studentId)
                putExtra(KEY_CLASS, className)
                putExtra(KEY_EMAIL, email)
                putExtra(KEY_PHONE, phone)
            }
            editLauncher.launch(intent)
        }
    }

    private fun orDefault(s: String) = if (s.isBlank()) "Chưa có thông tin" else s

    private fun showProfile() {
        tvName.text = "Họ tên: ${orDefault(name)}"
        tvStudentId.text = "MSSV: ${orDefault(studentId)}"
        tvClass.text = "Lớp: ${orDefault(className)}"
        tvEmail.text = "Email: ${orDefault(email)}"
        tvPhone.text = "SĐT: ${orDefault(phone)}"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_NAME, name)
        outState.putString(KEY_ID, studentId)
        outState.putString(KEY_CLASS, className)
        outState.putString(KEY_EMAIL, email)
        outState.putString(KEY_PHONE, phone)
        outState.putString(KEY_AVATAR, avatarUri?.toString())
    }

    override fun onStart() { super.onStart(); Log.d(TAG, "MainActivity: onStart()") }
    override fun onResume() { super.onResume(); Log.d(TAG, "MainActivity: onResume()") }
    override fun onPause() { super.onPause(); Log.d(TAG, "MainActivity: onPause()") }
    override fun onStop() { super.onStop(); Log.d(TAG, "MainActivity: onStop()") }
    override fun onRestart() { super.onRestart(); Log.d(TAG, "MainActivity: onRestart()") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "MainActivity: onDestroy()") }
}