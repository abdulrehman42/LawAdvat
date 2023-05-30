package com.example.lawadvat.ui.home.adapter.lawyers

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lawadvat.R


class LawyerAdapter(val context:Context, var list: ArrayList<Lawyer?>):RecyclerView.Adapter<LawyerAdapter.Holder>() {
    class Holder(val v:View):RecyclerView.ViewHolder(v)
    {
        val name=v.findViewById<TextView>(R.id.name)
        val des=v.findViewById<TextView>(R.id.description)
        val fee=v.findViewById<TextView>(R.id.fee)
        val img=v.findViewById<ImageView>(R.id.profile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.custom_lawyerhire,parent,false)
        return Holder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val l=list[position]
        holder.name.text=l?.name
        holder.des.text=l?.description
        holder.fee.text=l?.fee
        Glide.with(context)
            .load(l?.img)
            .into(holder.img)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setUsersList(users: ArrayList<Lawyer?>) {
        list=users
        notifyDataSetChanged()
    }
}