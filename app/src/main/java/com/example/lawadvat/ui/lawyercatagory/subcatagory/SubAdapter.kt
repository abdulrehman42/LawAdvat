package com.example.lawadvat.ui.lawyercatagory.subcatagory

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

class SubAdapter(val context: Context,val list:ArrayList<Requirement?>):RecyclerView.Adapter<SubAdapter.Holder>() {
    class Holder(val v: View): RecyclerView.ViewHolder(v)
    {
        val name=v.findViewById<TextView>(R.id.name1)
        val des=v.findViewById<TextView>(R.id.description1)
        val fee=v.findViewById<TextView>(R.id.fee11)
        val img=v.findViewById<ImageView>(R.id.profile1)
        val match=v.findViewById<TextView>(R.id.first_sp)
        val location=v.findViewById<TextView>(R.id.location11)
        val button=v.findViewById<Button>(R.id.hire_btn1)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.custom_subcatagory,parent,false)
        return Holder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val l=list[position]
        holder.name.text=l?.name
        holder.des.text=l?.description
        holder.fee.text=l?.fee+"PKR"
        holder.match.text=l?.first_specilize/*+"\t"+l?.second_specilize+"\t"+l?.third_specilize+"\t"+l?.fourth_specilize*/
        holder.location.text=l?.city
        Glide.with(context)
            .load(l?.img)
            .into(holder.img)
        holder.button.setOnClickListener {
           /* val bundle=Bundle()
            val hirelawyer=HireLawyer()
            hirelawyer.arguments=bundle*/

            val intent= Intent(context, HireProfile::class.java)
            intent.putExtra(Variablecontain.name,l?.name)
            intent.putExtra(Variablecontain.img,l?.img)
            intent.putExtra(Variablecontain.location,l?.office_location)
            intent.putExtra(Variablecontain.exp,l?.experince)
            intent.putExtra(Variablecontain.fee,l?.fee)
            intent.putExtra(Variablecontain.des,l?.description)
            intent.putExtra(Variablecontain.case1,l?.first_specilize)
            intent.putExtra(Variablecontain.case2,l?.second_specilize)
            intent.putExtra(Variablecontain.case3,l?.third_specilize)
            intent.putExtra(Variablecontain.case4,l?.fourth_specilize)
            intent.putExtra(Variablecontain.id,l?.id)
            context.startActivity(intent)
        }
    }

}