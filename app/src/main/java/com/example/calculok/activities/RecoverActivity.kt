package com.example.calculok.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.calculok.LoginActivity
import com.example.calculok.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RecoverActivity : AppCompatActivity() {

    private lateinit var emailRecover : EditText
    private lateinit var recoverButton: Button
    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        init()

        recoverButton.setOnClickListener {

            val email = emailRecover.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                auth.sendPasswordResetEmail(email).addOnCompleteListener { mission ->
                    if (mission.isSuccessful) {
                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {
                        return@addOnCompleteListener
                    }
                }
            }

        }

    }

    private fun init() {

        emailRecover = findViewById(R.id.emailRecover)
        recoverButton = findViewById(R.id.recoverButton)

    }



}