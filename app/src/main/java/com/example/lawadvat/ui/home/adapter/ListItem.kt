package com.example.lawadvat.ui.home.adapter

import com.example.lawadvat.R

class ListItem {
    companion object
    {
        fun getlist():ArrayList<Services> {
            val data = ArrayList<Services>()
            data.add(Services(R.drawable.attorney,"Lawyer"))
            data.add(Services(R.drawable.lawyers,"atterny"))
            data.add(Services(R.drawable.lawyer,"Criminal"))
            return data
        }

    }
}