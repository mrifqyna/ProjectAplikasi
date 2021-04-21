package com.example.projectaplikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var tv_register = findViewById<TextView>(R.id.tvRegister)
        var et_username = findViewById<EditText>(R.id.etUser)
        var et_password = findViewById<EditText>(R.id.etPassword)
        var btn_login = findViewById<Button>(R.id.btnLogin)

        btn_login.setOnClickListener {
            if (et_username.text.toString().length < 6) {
                Toast.makeText(this,"Username minimal 6 karakter",Toast.LENGTH_SHORT).show()
            } else if(et_password.text.toString().length < 8) {
                Toast.makeText(this,"Password minimal 8 karakter",Toast.LENGTH_SHORT).show()
            }
        }
        tv_register.setOnClickListener {
            intent = Intent(this,Register::class.java)
            startActivity(intent)
        }
    }
}