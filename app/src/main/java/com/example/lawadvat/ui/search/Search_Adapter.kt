package com.example.lawadvat.ui.search

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lawadvat.R
import com.example.lawadvat.ui.Variablecontain
import com.example.lawadvat.ui.become.entity.Requirement
import com.example.lawadvat.ui.home.adapter.lawyers.HireProfile

class Search_Adapter (val context: Context, var list: ArrayList<Requirement?>):
    RecyclerView.Adapter<Search_Adapter.Holder>() {
    class Holder(val v: View) : RecyclerView.ViewHolder(v) {
        val name = v.findViewById<TextView>(R.id.search_name)
        val des = v.findViewById<TextView>(R.id.search_catagory)
        val fee = v.findViewById<TextView>(R.id.search_city)
        val img = v.findViewById<ImageView>(R.id.search_profile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_search, parent, false)
        return Holder(v)
    }
        override fun getItemCount(): Int {
        return list.size
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val l = list[position]
        holder.name.text = l?.name
        holder.des.text = "specilize practice:"+l?.first_specilize
        holder.fee.text = "city:"+l?.city
        Glide.with(context)
            .load(l?.img)
            .into(holder.img)
        holder.itemView.setOnClickListener {
            /*val bundle=Bundle()
            val hirelawyer=HireLawyer()
            hirelawyer.arguments=bundle*/

            val intent = Intent(context, HireProfile::class.java)
            intent.putExtra(Variablecontain.name, l?.name)
            intent.putExtra(Variablecontain.img, l?.img)
            intent.putExtra(Variablecontain.location, l?.office_location)
            intent.putExtra(Variablecontain.exp, l?.experince)
            intent.putExtra(Variablecontain.fee, l?.fee)
            intent.putExtra(Variablecontain.des, l?.description)
            intent.putExtra(Variablecontain.case1, l?.first_specilize)
            intent.putExtra(Variablecontain.case2, l?.second_specilize)
            intent.putExtra(Variablecontain.case3, l?.third_specilize)
            intent.putExtra(Variablecontain.case4, l?.fourth_specilize)
            intent.putExtra(Variablecontain.id, l?.id)
            context.startActivity(intent)
        }
    }
}