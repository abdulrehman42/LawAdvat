package com.example.lawadvat.ui.appintment.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawadvat.R
import com.example.lawadvat.ui.appintment.appointment.Appoint

class AppointmentAdpter(val context: Context, val list:ArrayList<Appoint>):RecyclerView.Adapter<AppointmentAdpter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        val name=view.findViewById<TextView>(R.id.name_item)
        val image=view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}