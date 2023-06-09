package com.example.lawadvat.ui.lawyercatagory

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawadvat.R
import com.example.lawadvat.ui.home.adapter.Services
import com.example.lawadvat.ui.lawyercatagory.subcatagory.Subcatagory

class CAtagoryAdapter(val context: Context,val list:ArrayList<Services>):RecyclerView.Adapter<CAtagoryAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val name=view.findViewById<TextView>(R.id.name_item_c)
        val image=view.findViewById<ImageView>(R.id.image_item_c)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custum_catagory, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val l=list[position]
        holder.name.text=l.name
        holder.image.setImageResource(l.img)
        l.setOnClickListener {
            val intent= Intent(context,Subcatagory::class.java)
            context.startActivity(intent)
        }
    }
}