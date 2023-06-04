package com.example.lawadvat.ui.messege.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.lawadvat.R
import com.example.lawadvat.ui.messege.Messege
import com.google.firebase.auth.FirebaseAuth

class MessegeAdapter(val context: Context,val list:ArrayList<Messege?>):RecyclerView.Adapter<MessegeAdapter.ViewHolder>() {
   val user_id=FirebaseAuth.getInstance().currentUser?.uid.toString()
    class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    {
        val reciver_linear=view.findViewById<LinearLayout>(R.id.reciver_messege_linear)
        val sender_linear=view.findViewById<LinearLayout>(R.id.sender_messege_linear)
        val send_msg=view.findViewById<TextView>(R.id.sender_msg)
        val recive_msg=view.findViewById<TextView>(R.id.reciver_msg)
        val sender_time=view.findViewById<TextView>(R.id.sendr_time)
        val reciver_time=view.findViewById<TextView>(R.id.reciver_time)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout=LayoutInflater.from(parent.context).inflate(R.layout.custom_messege,parent,false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg=list[position]
        if (msg?.sender_id==user_id)
        {
            holder.reciver_linear.isVisible=false
            holder.sender_linear.isVisible=true
            holder.send_msg.text= msg.text.toString()
            holder.sender_time.text=msg.time.toString()
        }
        else{
            holder.reciver_linear.isVisible=true
            holder.sender_linear.isVisible=false
            holder.recive_msg.text=msg?.text.toString()
            holder.reciver_time.text=msg?.time.toString()
        }
    }
}