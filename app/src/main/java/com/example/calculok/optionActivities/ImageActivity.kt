package com.example.calculok.optionActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.calculok.R
import com.example.calculok.activities.MainActivity
import com.example.calculok.models.UserImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ImageActivity : AppCompatActivity() {

    val refImage = FirebaseDatabase.getInstance().getReference("UserImage")
    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val inputUrl = findViewById<EditText>(R.id.inputUrl)
        val change = findViewById<Button>(R.id.changeButton)

        change.setOnClickListener {

            val userimage = UserImage(inputUrl.text.toString())
            refImage.child(auth.currentUser?.uid!!).setValue(userimage)

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }

    }



}