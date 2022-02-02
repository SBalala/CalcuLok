package com.example.calculok.fragments

import android.os.Bundle
import android.util.AndroidRuntimeException
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.calculok.R
import com.example.calculok.models.AchivementData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.lang.Error
import java.lang.NumberFormatException

class CalculatorFragment : Fragment(R.layout.fragment_calculator) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn0 = view.findViewById<Button>(R.id.btn0)
        val btn1 = view.findViewById<Button>(R.id.btn1)
        val btn2 = view.findViewById<Button>(R.id.btn2)
        val btn3 = view.findViewById<Button>(R.id.btn3)
        val btn4 = view.findViewById<Button>(R.id.btn4)
        val btn5 = view.findViewById<Button>(R.id.btn5)
        val btn6 = view.findViewById<Button>(R.id.btn6)
        val btn7 = view.findViewById<Button>(R.id.btn7)
        val btn8 = view.findViewById<Button>(R.id.btn8)
        val btn9 = view.findViewById<Button>(R.id.btn9)
        val btnDivision = view.findViewById<Button>(R.id.btnDivision)
        val btnMultiplication = view.findViewById<Button>(R.id.btnMultiplication)
        val btnMinus = view.findViewById<Button>(R.id.btnMinus)
        val btnPlus = view.findViewById<Button>(R.id.btnPlus)
        val btnEquals = view.findViewById<Button>(R.id.btnEquals)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)
        val numLine = view.findViewById<TextView>(R.id.numLine)
        val refAchivement = FirebaseDatabase.getInstance().getReference("AchivementInfo")
        val auth = FirebaseAuth.getInstance()



        btn0.setOnClickListener {
            numLine.append("0")
        }

        btn1.setOnClickListener {
            numLine.append("1")
        }

        btn2.setOnClickListener {
            numLine.append("2")
        }

        btn3.setOnClickListener {
            numLine.append("3")
        }

        btn4.setOnClickListener {
            numLine.append("4")
        }

        btn5.setOnClickListener {
            numLine.append("5")
        }

        btn6.setOnClickListener {
            numLine.append("6")
        }

        btn7.setOnClickListener {
            numLine.append("7")
        }

        btn8.setOnClickListener {
            numLine.append("8")
        }

        btn9.setOnClickListener {
            numLine.append("9")
        }

        btnPlus.setOnClickListener {
            numLine.append(" + ")
        }

        btnMinus.setOnClickListener {
            numLine.append(" - ")
        }

        btnDivision.setOnClickListener {
            numLine.append(" / ")
        }

        btnMultiplication.setOnClickListener {
            numLine.append(" * ")
        }

        btnDelete.setOnClickListener {
            numLine.text = ""
        }

        btnEquals.setOnClickListener {

            val string = numLine.text.toString()

            if (string == "1121") {

                val image = "data:image/jpeg;base64,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"
                val name = "DIDGORI!"
                val description = "da adga davit..."

                numLine.setText("Achivement Unlocked!")

                val achivementdata = AchivementData(image, name, description)
                refAchivement.child(auth.currentUser?.uid!!).child("DIDGORI").setValue(achivementdata)

            } else if (string == "69") {

                val image = "https://i1.sndcdn.com/artworks-9b5uKQs6rEecP9Pc-XTz2SA-t500x500.jpg"
                val name = "Johny"
                val description = "You Naughty Naughty!"

                numLine.setText("Achivement Unlocked!")

                val achivementdata = AchivementData(image, name, description)
                refAchivement.child(auth.currentUser?.uid!!).child("Johny").setValue(achivementdata)


            } else if (string == "2008") {

                val image = "https://yt3.ggpht.com/ytc/AKedOLR6vLMrT_Rzq8QLFS3wclBNVzivQNIbmqcrxIuw=s900-c-k-c0x00ffffff-no-rj"
                val name = "Amerikanec Bliad?!"
                val description = "Rusets ra...?"

                numLine.setText("Achivement Unlocked!")

                val achivementdata = AchivementData(image, name, description)
                refAchivement.child(auth.currentUser?.uid!!).child("Amerikanec").setValue(achivementdata)


            } else {

                try {

                    val nums = numLine.text.toString().split(" ")

                    val firstNum = nums[0].toInt()
                    val action = nums[1]
                    val secondNum = nums[2].toInt()

                    if (action == "+") {

                        val answer = firstNum + secondNum
                        numLine.text = answer.toString()

                    } else if (action == "-") {

                        val answer = firstNum - secondNum
                        numLine.text = answer.toString()

                    } else if (action == "/") {

                        val answer = firstNum / secondNum
                        numLine.text = answer.toString()

                    } else if (action == "*") {

                        val answer = firstNum * secondNum
                        numLine.text = answer.toString()

                    } else {

                        numLine.text = "Error!"

                    }

                } catch (e: Exception) {
                    numLine.text = "Error!"
                }
            }
        }

    }
}