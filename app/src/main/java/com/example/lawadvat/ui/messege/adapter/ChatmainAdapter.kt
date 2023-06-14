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
import android.content.Intent
import android.widget.ImageView
import com.example.lawadvat.ui.Variablecontain
import com.example.lawadvat.ui.messege.ChatActivity
import com.google.firebase.auth.FirebaseAuth

class ChatmainAdapter(val context: Context?, val list: ArrayList<Messege?>):RecyclerView.Adapter<ChatmainAdapter.ViewHolder>() {
    val user = FirebaseAuth.getInstance().currentUser?.uid.toString()

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.chatuser_name)
        val image = view.findViewById<ImageView>(R.id.chatuser_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_chatlistview, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val l = list[position]
        if (l != null) {
            if (l.sender_id == user) {
                holder.name.text = l.reciver_name.toString()
                if (context != null) {
                    Glide.with(context)
                        .load(l.reciver_image)
                        .into(holder.image)
                }
                holder.itemView.setOnClickListener {
                    val intent = Intent(context, ChatActivity::class.java)
                    intent.putExtra(Variablecontain.name, l?.reciver_name)
                    intent.putExtra(Variablecontain.sender_id, l?.sender_id)
                    intent.putExtra(Variablecontain.reciver_id, l?.reciver_id)
                    intent.putExtra(Variablecontain.img,l.sender_image)
                    intent.putExtra(Variablecontain.reciver_img,l.reciver_image)
                    context?.startActivity(intent)
                }
            } else {
                holder.name.text = l.sender_name.toString()
                if (context != null) {
                    Glide.with(context)
                        .load(l.sender_image)
                        .into(holder.image)
                }
                holder.itemView.setOnClickListener {
                    val intent = Intent(context, ChatActivity::class.java)
                    intent.putExtra(Variablecontain.name, l?.sender_name)
                    intent.putExtra(Variablecontain.sender_id, l?.sender_id)
                    intent.putExtra(Variablecontain.reciver_id, l?.reciver_id)
                    intent.putExtra(Variablecontain.email,l.email)
                    intent.putExtra(Variablecontain.reciver_img,l.sender_image)
                    context?.startActivity(intent)
                }
            }
        }
    }
}