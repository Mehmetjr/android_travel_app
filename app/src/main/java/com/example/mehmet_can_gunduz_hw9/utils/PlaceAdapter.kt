package com.example.mehmet_can_gunduz_hw9.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.mehmet_can_gunduz_hw9.R
import com.example.mehmet_can_gunduz_hw9.models.Place
import com.example.mehmet_can_gunduz_hw9.models.Places

class PlaceAdapter(private val placeList : MutableList<Places>):RecyclerView.Adapter<PlaceAdapter.MyViewHolder>() {


    class MyViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val title=itemView.findViewById<TextView>(R.id.txt_home_title)
        val city=itemView.findViewById<TextView>(R.id.txt_home_city)
        val note=itemView.findViewById<TextView>(R.id.txt_home_note)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = placeList[position]

        holder.title.text = currentItem.title
        holder.city.text = currentItem.city
        holder.note.text = currentItem.note

    }




}