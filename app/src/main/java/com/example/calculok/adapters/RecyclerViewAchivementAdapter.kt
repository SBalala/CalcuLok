package com.example.calculok.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.calculok.R
import com.example.calculok.models.AchivementData


class RecyclerViewAchivementAdapter(val achivementList: List<AchivementData>) : RecyclerView.Adapter<RecyclerViewAchivementAdapter.AchivementViewHolder>() {


    class AchivementViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var achivementImage : ImageView
        private lateinit var achivementName : TextView
        private lateinit var achivementDescription : TextView

        init {
            achivementImage = itemView.findViewById(R.id.achivementImage)
            achivementName = itemView.findViewById(R.id.achivementName)
            achivementDescription = itemView.findViewById(R.id.achivementDescription)
        }

        fun setData(achivement: AchivementData) {

            Glide.with(itemView).load(achivement.url).into(achivementImage)
            achivementName.text = achivement.name
            achivementDescription.text = achivement.description

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchivementViewHolder {
        val achivementView = LayoutInflater.from(parent.context).inflate(R.layout.item_achivement, parent, false)
        return AchivementViewHolder(achivementView)
    }

    override fun onBindViewHolder(holder: AchivementViewHolder, position: Int) {
        holder.setData(achivementList[position])
    }

    override fun getItemCount(): Int {
        return achivementList.size
    }

}