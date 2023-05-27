package com.example.lawadvat.ui.home.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawadvat.R

class SpecilizeRecyclerviw(val context:Context, private val list:ArrayList<Services>):RecyclerView.Adapter<SpecilizeRecyclerviw.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    {
        val name=view.findViewById<TextView>(R.id.name_item)
        val image=view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.specilize, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val l=list[position]
        holder.name.text=l.name
        holder.image.setImageResource(l.img)
    }
}