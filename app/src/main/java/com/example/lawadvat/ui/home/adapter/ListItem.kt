package com.example.lawadvat.ui.home.adapter

import com.example.lawadvat.R

class ListItem {
    companion object
    {
        fun getlist():ArrayList<Services> {
            val data = ArrayList<Services>()
            data.add(Services(R.drawable.lawyers,"Murder"))
            data.add(Services(R.drawable.lawyers,"Assault"))
            data.add(Services(R.drawable.lawyers,"Theft"))
            data.add(Services(R.drawable.lawyers,"Robbery"))
            data.add(Services(R.drawable.lawyers,"Offenses"))
            data.add(Services(R.drawable.lawyers,"Fraud"))
            data.add(Services(R.drawable.lawyers,"Cybercrime"))
            data.add(Services(R.drawable.lawyers,"Kidnapping"))
            data.add(Services(R.drawable.lawyers,"Personal Injury"))
            data.add(Services(R.drawable.lawyers,"Property Disputes"))
            data.add(Services(R.drawable.lawyers,"Contract Disputes"))
            data.add(Services(R.drawable.lawyers,"Family Law"))
            data.add(Services(R.drawable.lawyers,"Divorce"))
            data.add(Services(R.drawable.lawyers,"Child Custody"))
            data.add(Services(R.drawable.lawyers,"Child Suppor"))
            data.add(Services(R.drawable.lawyers,"Adoption"))
            data.add(Services(R.drawable.lawyers,"Domestic Violence"))
            data.add(Services(R.drawable.lawyers,"Immigration Appeals"))
            data.add(Services(R.drawable.lawyers,"Regulatory Compliance"))
            data.add(Services(R.drawable.lawyers,"Tax Appeals"))
            data.add(Services(R.drawable.lawyers,"Government Agency Disputes"))
            data.add(Services(R.drawable.lawyers,"Civil Rights Violations"))
            data.add(Services(R.drawable.lawyers,"Freedom of Speech"))
            data.add(Services(R.drawable.lawyers,"Employment Law"))
            data.add(Services(R.drawable.lawyers,"Pollution"))
            data.add(Services(R.drawable.lawyers,"Climate Change "))
            data.add(Services(R.drawable.lawyers,"Environmental Cleanup"))
            return data
        }

    }
}