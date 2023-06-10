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
import com.example.lawadvat.ui.Variablecontain
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
        holder.itemView.setOnClickListener {
            move_on(position,context)
            }
        }
    }

    private fun move_on(position: Int, context: Context) {
        val intent= Intent(context,Subcatagory::class.java)
        when(position){
            0->{
                intent.putExtra(Variablecontain.data,"Murder")
                context.startActivity(intent)
            }1->
        {
            intent.putExtra(Variablecontain.data,"Assault")
            context.startActivity(intent)
        }2->
        {
            intent.putExtra(Variablecontain.data,"Theft")
            context.startActivity(intent)
        }
            3->{
                intent.putExtra(Variablecontain.data,"Robbery")
                context.startActivity(intent)
            }4->
        {
            intent.putExtra(Variablecontain.data,"Offenses")
            context.startActivity(intent)
        }5->
        {
            intent.putExtra(Variablecontain.data,"Fraud")
            context.startActivity(intent)
        }6->{
            intent.putExtra(Variablecontain.data,"Cybercrime")
            context.startActivity(intent)
        }
            7->{
                intent.putExtra(Variablecontain.data,"Kidnapping")
                context.startActivity(intent)
            }8->
        {
            intent.putExtra(Variablecontain.data,"Personal Injury")
            context.startActivity(intent)
        }9->
        {
            intent.putExtra(Variablecontain.data,"Property Disputes")
            context.startActivity(intent)
        }
            10->{
                intent.putExtra(Variablecontain.data,"Contract Disputes")
                context.startActivity(intent)
            }11->
        {
            intent.putExtra(Variablecontain.data,"Family Law")
            context.startActivity(intent)
        }12->
        {
            intent.putExtra(Variablecontain.data,"Divorce")
            context.startActivity(intent)
        }13->{
            intent.putExtra(Variablecontain.data,"Child Custody")
            context.startActivity(intent)
        }
            14->{
                intent.putExtra(Variablecontain.data,"Child Suppor")
                context.startActivity(intent)
            }15->
        {
            intent.putExtra(Variablecontain.data,"Adoption")
            context.startActivity(intent)
        }16->
        {
            intent.putExtra(Variablecontain.data,"Domestic Violence")
            context.startActivity(intent)
        }
            17->{
                intent.putExtra(Variablecontain.data,"Immigration Appeals")
                context.startActivity(intent)
            }18->
        {
            intent.putExtra(Variablecontain.data,"Regulatory Compliance")
            context.startActivity(intent)
        }19->
        {
            intent.putExtra(Variablecontain.data,"Tax Appeals")
            context.startActivity(intent)
        }20->{
            intent.putExtra(Variablecontain.data,"Government Agency Disputes")
            context.startActivity(intent)
        }21->{
            intent.putExtra(Variablecontain.data,"Civil Rights Violations")
            context.startActivity(intent)
        }
            22->{
                intent.putExtra(Variablecontain.data,"Freedom of Speech")
                context.startActivity(intent)
            }23->
        {
            intent.putExtra(Variablecontain.data,"Employment Law")
            context.startActivity(intent)
        }24->
        {
            intent.putExtra(Variablecontain.data,"Pollution")
            context.startActivity(intent)
        }
            25->{
                intent.putExtra(Variablecontain.data,"Climate Change")
                context.startActivity(intent)
            }26->
        {
            intent.putExtra(Variablecontain.data,"Environmental Cleanup")
            context.startActivity(intent)
        }
    }
}