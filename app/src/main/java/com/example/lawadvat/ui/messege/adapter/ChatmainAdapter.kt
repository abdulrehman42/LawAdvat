package com.example.lawadvat.ui.messege.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lawadvat.R
import com.example.lawadvat.ui.messege.Messege
import android.content.Context
import android.widget.ImageView

class ChatmainAdapter(val context: Context, val list: ArrayList<Messege?>):RecyclerView.Adapter<ChatmainAdapter.ViewHolder>() {
    class ViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val name=view.findViewById<TextView>(R.id.chatuser_name)
        val image=view.findViewById<ImageView>(R.id.chatuser_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout=LayoutInflater.from(parent.context).inflate(R.layout.custom_chatlistview,parent,false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val l=list[position]
        holder.name.text=l?.sender_name.toString()
        Glide.with(context)
            .load(l?.sender_image)
            .into(holder.image)
    }
}