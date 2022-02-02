package com.example.calculok.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.calculok.LoginActivity
import com.example.calculok.R
import com.example.calculok.models.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var usernameRegister : EditText
    private lateinit var emailRegister : EditText
    private lateinit var passwordRegister : EditText
    private lateinit var repeatRegister : EditText
    private lateinit var registerButton : Button
    private val auth = FirebaseAuth.getInstance()
    private val refInfo = FirebaseDatabase.getInstance().getReference("UserInfo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        init()

        setOnClickListeners()

    }

    private fun init() {

        usernameRegister = findViewById(R.id.usernameRegister)
        emailRegister = findViewById(R.id.emailRegister)
        passwordRegister = findViewById(R.id.passwordRegister)
        repeatRegister = findViewById(R.id.repeatRegister)
        registerButton = findViewById(R.id.register)

    }

    private fun setOnClickListeners() {

        registerButton.setOnClickListener {

            val username = usernameRegister.text.toString()
            val email = emailRegister.text.toString()
            val password = passwordRegister.text.toString()
            val repeat = repeatRegister.text.toString()


            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || repeat.isEmpty()) {
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password != repeat) {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { mission ->
                    if (mission.isSuccessful) {

                        val userinfo = UserInfo(username)
                        refInfo.child(auth.currentUser?.uid!!).setValue(userinfo)

                        startActivity(Intent(this, LoginActivity::class.java))

                    }

                }

            }

        }

    }

}