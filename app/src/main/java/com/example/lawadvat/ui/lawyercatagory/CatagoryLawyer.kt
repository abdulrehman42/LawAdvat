package com.example.lawadvat.ui.lawyercatagory

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lawadvat.R
import com.example.lawadvat.databinding.ActivityCatagoryLawyerBinding
import com.example.lawadvat.ui.home.adapter.ListItem
import com.example.lawadvat.ui.home.adapter.SpecilizeRecyclerviw

class CatagoryLawyer : AppCompatActivity() {
    lateinit var binding:ActivityCatagoryLawyerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCatagoryLawyerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Specilizations"
        recyclerview()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerview() {
        val adapter= CAtagoryAdapter(this, ListItem.getlist())
        binding.recyclerCatagory.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.recyclerCatagory.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerCatagory.layoutManager=gridLayoutManager
        binding.recyclerCatagory.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}