package com.example.lawadvat.ui.lawyercatagory.subcatagory

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lawadvat.databinding.ActivitySubcatagoryBinding
import com.example.lawadvat.ui.Variablecontain
import com.example.lawadvat.ui.become.entity.Requirement
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class Subcatagory : AppCompatActivity() {
    lateinit var binding:ActivitySubcatagoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySubcatagoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        in_it()
    }

    private fun in_it() {
        val data=intent.getStringExtra(Variablecontain.data)
        val objectList=ArrayList<Requirement?>()
        val databaseReference = FirebaseDatabase.getInstance().getReference("Lawyer")
        val s="criminal"
        val query: Query = databaseReference.orderByChild("first_specilize").equalTo(data.toString())
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (datas in dataSnapshot.children) {
                        //val name = datas.child("name").value.toString()
                        val requirement: Requirement? = datas.getValue(Requirement::class.java)
                        //objectList.add(Requirement(name = name))
                        objectList.add(requirement)
                        recyclerview_hire(objectList)
                    }
                }else{
                    Toast.makeText(this@Subcatagory,"Lawyer not Available",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerview_hire(list: ArrayList<Requirement?>) {
        val adapter= SubAdapter(this,list)
        binding.subRecyclerview.setHasFixedSize(true)
        binding.subRecyclerview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.subRecyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}