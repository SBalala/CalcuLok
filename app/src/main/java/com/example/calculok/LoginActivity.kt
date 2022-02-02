package com.example.calculok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.calculok.activities.MainActivity
import com.example.calculok.activities.RecoverActivity
import com.example.calculok.activities.RegistrationActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var emailLogin : EditText
    private lateinit var passwordLogin : EditText
    private lateinit var loginButton : Button
    private lateinit var registerButton: Button
    private lateinit var recoverButton: Button
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (auth.currentUser?.uid != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setContentView(R.layout.activity_login)

        init()

        onClickListeners()

    }

    private fun init() {

        emailLogin = findViewById(R.id.emailLogin)
        passwordLogin = findViewById(R.id.passwordLogin)
        loginButton = findViewById(R.id.buttonLogin)
        registerButton = findViewById(R.id.buttonRegister)
        recoverButton = findViewById(R.id.buttonRecover)

    }

    private fun onClickListeners() {

        loginButton.setOnClickListener {

            val email = emailLogin.text.toString()
            val password = passwordLogin.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter something!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { mission ->
                    if (mission.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        return@addOnCompleteListener
                    }
                }
            }

        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        recoverButton.setOnClickListener {
            startActivity(Intent(this, RecoverActivity::class.java))
        }

    }

}