package com.example.lawadvat.ui.home.adapter.lawyers

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.lawadvat.R
import com.example.lawadvat.databinding.ActivityHireProfileBinding
import com.example.lawadvat.ui.Variablecontain
import com.example.lawadvat.ui.appintment.appointment.Appointment
import com.example.lawadvat.ui.become.entity.Requirement
import java.io.Serializable
import java.util.ArrayList

class HireProfile : AppCompatActivity() {
    lateinit var binding:ActivityHireProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHireProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        supportActionBar?.customView=binding.toolbarFirm
        binding.toolbarFirm.setNavigationOnClickListener {
            finish()
        }
        get_result()
        binding.appointmentBtn.setOnClickListener {
            val name=intent.getStringExtra(Variablecontain.name)
            val img=intent.getStringExtra(Variablecontain.img)
            val location=intent.getStringExtra(Variablecontain.location)
            val exp=intent.getStringExtra(Variablecontain.exp)
            val fee=intent.getStringExtra(Variablecontain.fee)
            val intent=Intent(this, Appointment::class.java)
            intent.putExtra(Variablecontain.name,name)
            intent.putExtra(Variablecontain.img,img)
            intent.putExtra(Variablecontain.location,location)
            intent.putExtra(Variablecontain.fee,fee)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun get_result() {
        val name=intent.getStringExtra(Variablecontain.name)
        val img=intent.getStringExtra(Variablecontain.img)
        val location=intent.getStringExtra(Variablecontain.location)
        val exp=intent.getStringExtra(Variablecontain.exp)
        val fee=intent.getStringExtra(Variablecontain.fee)
        val des=intent.getStringExtra(Variablecontain.des)
        val case1=intent.getStringExtra(Variablecontain.case1)
        val case2=intent.getStringExtra(Variablecontain.case2)
        val case3=intent.getStringExtra(Variablecontain.case3)
        val case4=intent.getStringExtra(Variablecontain.case4)
        binding.profileLawyerName1.text=name.toString()
        binding.location1.text=location.toString()
        binding.experience1.text="Experience:"+exp.toString()
        binding.fee1.text="Fee:"+fee.toString()+"PKR"
        binding.des1.text=des.toString()
        binding.first1.text="\u25BA"+case1.toString()
        binding.second1.text="\u25BA"+case2.toString()
        binding.third1.text="\u25BA"+case3.toString()
        binding.fourth1.text="\u25BA"+case4.toString()
        Glide.with(this).load(img).into(binding.profileLawyerImage3)
    }
}