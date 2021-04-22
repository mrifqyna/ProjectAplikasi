package com.example.projectaplikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        var tv_login = findViewById<TextView>(R.id.tvLogin)
        var btn_register = findViewById<Button>(R.id.btnRegister)
        var et_username = findViewById<EditText>(R.id.etUsername)
        var et_email = findViewById<EditText>(R.id.etEmail)
        var et_pass1 = findViewById<EditText>(R.id.etPass1)
        var et_pass2 = findViewById<EditText>(R.id.etPass2)

        tv_login.setOnClickListener{
            intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
        btn_register.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_pass1.text.toString().trim()
            if (et_username.text.toString().length < 6) {
                Toast.makeText(this, "Username minimal 6 karakter",Toast.LENGTH_SHORT).show()
            } else if(!et_email.text.toString().contains("@")) {
                Toast.makeText(this,"Email tidak valid",Toast.LENGTH_SHORT).show()
            } else if(!et_email.text.toString().contains(".")) {
                Toast.makeText(this,"Email tidak valid",Toast.LENGTH_SHORT).show()
            } else if (et_pass1.text.toString().length < 8) {
                Toast.makeText(this,"Password minimal 8 karakter",Toast.LENGTH_SHORT).show()
            } else if (et_pass2.text.toString() != et_pass1.text.toString()) {
                Toast.makeText(this,"Password tidak sama",Toast.LENGTH_SHORT).show()
            }
            register(email, password)
        }
    }

    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Intent(this@Register, Homepage::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                        }
                    } else {
                        Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
    }

    override fun onStart() {
        super.onStart()
         if (auth.currentUser != null){
             Intent(this@Register, Homepage::class.java).also {
                 it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                 startActivity(it)
             }
         }
    }
}