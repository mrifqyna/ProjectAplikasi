package com.example.projectaplikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        var tv_register = findViewById<TextView>(R.id.tvRegister)
        var et_email = findViewById<EditText>(R.id.etEmail)
        var et_password = findViewById<EditText>(R.id.etPassword)
        var btn_login = findViewById<Button>(R.id.btnSignin)

        btn_login.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            if (!et_email.text.toString().contains("@")) {
                Toast.makeText(this,"Email tidak valid",Toast.LENGTH_SHORT).show()
            } else if(!et_email.text.toString().contains(".")) {
                Toast.makeText(this,"Email tidak valid",Toast.LENGTH_SHORT).show()
            } else if(et_password.text.toString().length < 8) {
                Toast.makeText(this,"Password minimal 8 karakter",Toast.LENGTH_SHORT).show()
            }
            login(email, password)
        }

        tv_register.setOnClickListener {
            intent = Intent(this,Register::class.java)
            startActivity(intent)
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Intent(this@Login, Homepage::class.java).also {intent ->
                            intent .flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                    } else {
                        Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this@Login, Homepage::class.java).also{intent ->
                intent .flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}