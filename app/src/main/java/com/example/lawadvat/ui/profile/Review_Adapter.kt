package com.example.lawadvat.ui.profile

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawadvat.R
import com.example.lawadvat.ui.review.Review_entity
class Review_Adapter(val context: Context, var list: ArrayList<Review_entity?>):
    RecyclerView.Adapter<Review_Adapter.Holder>() {
    class Holder(val v: View): RecyclerView.ViewHolder(v)
    {
        val name=v.findViewById<TextView>(R.id.review_writer)
        val review=v.findViewById<TextView>(R.id.review_show)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.custom_review,parent,false)
        return Holder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val l=list[position]
        holder.name.text=l?.send_review_name
        holder.review.text=l?.review
    }
}