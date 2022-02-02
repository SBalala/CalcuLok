package com.example.calculok.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.calculok.LoginActivity
import com.example.calculok.R
import com.example.calculok.adapters.RecyclerViewAchivementAdapter
import com.example.calculok.models.AchivementData
import com.example.calculok.models.UserImage
import com.example.calculok.models.UserInfo
import com.example.calculok.optionActivities.ImageActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    val refInfo = FirebaseDatabase.getInstance().getReference("UserInfo")
    val refImage = FirebaseDatabase.getInstance().getReference("UserImage")
    val refAchivement = FirebaseDatabase.getInstance().getReference("AchivementInfo")
    val auth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = view.findViewById<TextView>(R.id.username)
        val changeBtn = view.findViewById<Button>(R.id.changeBtn)
        val image = view.findViewById<ImageView>(R.id.profileImage)
        val logOut = view.findViewById<Button>(R.id.logOutButton)
        val RCview = view.findViewById<RecyclerView>(R.id.RCview)


        refAchivement.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val achivementInfo = snapshot.children.toList()

                val achivement = ArrayList<AchivementData> ()

                for (i in achivementInfo) {

                    achivement.add(

                        i.getValue(AchivementData::class.java) ?:return

                    )

                }

                RCview.layoutManager = LinearLayoutManager(activity)
                RCview.adapter = RecyclerViewAchivementAdapter(achivement)

            }

            override fun onCancelled(error: DatabaseError) {
                return
            }

        })

        changeBtn.setOnClickListener {

            startActivity(Intent(activity, ImageActivity::class.java))

        }

        logOut.setOnClickListener {

            auth.signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()

        }


        refInfo.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val info = snapshot.getValue(UserInfo::class.java) ?:return
                username.text = info.username
            }

            override fun onCancelled(error: DatabaseError) {
                return
            }


        })

        refImage.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val url = snapshot.getValue(UserImage::class.java) ?:return
                Glide.with(this@ProfileFragment).load(url.imageUrl)
                    .placeholder(R.drawable.image_asset).into(image)

            }

            override fun onCancelled(error: DatabaseError) {
                return
            }

        })

    }



}