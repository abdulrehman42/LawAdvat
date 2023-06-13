package com.example.lawadvat.ui.home.adapter.lawyers

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.lawadvat.databinding.ActivityHireProfileBinding
import com.example.lawadvat.ui.Variablecontain
import com.example.lawadvat.ui.appintment.appointment.Appointment
import com.example.lawadvat.ui.messege.ChatActivity
import com.example.lawadvat.ui.profile.Dispute
import com.example.lawadvat.ui.review.Review_entity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HireProfile : AppCompatActivity() {
    lateinit var binding:ActivityHireProfileBinding
    var num=0
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
            val editor =
                getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
            editor.putString("date",null)
            editor.apply()
            val name=intent.getStringExtra(Variablecontain.name)
            val img=intent.getStringExtra(Variablecontain.img)
            val location=intent.getStringExtra(Variablecontain.location)
            val fee=intent.getStringExtra(Variablecontain.fee)
            val email=intent.getStringExtra(Variablecontain.email)
            val intent=Intent(this, Appointment::class.java)
            intent.putExtra(Variablecontain.name,name)
            intent.putExtra(Variablecontain.img,img)
            intent.putExtra(Variablecontain.location,location)
            intent.putExtra(Variablecontain.email,email)
            intent.putExtra(Variablecontain.fee,fee)
            startActivity(intent)
        }
        binding.report.setOnClickListener {
            val name=intent.getStringExtra(Variablecontain.name)
            val img=intent.getStringExtra(Variablecontain.img)
            val email=intent.getStringExtra(Variablecontain.email)
            val intent=Intent(this,Dispute::class.java)
            intent.putExtra(Variablecontain.name,name)
            intent.putExtra(Variablecontain.img,img)
            intent.putExtra(Variablecontain.email,email)
            startActivity(intent)

        }
        binding.msgBtn.setOnClickListener {
            val layer_id=intent.getStringExtra(Variablecontain.id)
            val name=intent.getStringExtra(Variablecontain.name)
            val img=intent.getStringExtra(Variablecontain.img)
            val email=intent.getStringExtra(Variablecontain.email)
            val intent=Intent(this,ChatActivity::class.java)
            intent.putExtra(Variablecontain.name,name)
            intent.putExtra(Variablecontain.id,layer_id)
            intent.putExtra(Variablecontain.img2,img)
            intent.putExtra(Variablecontain.email,email)
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
       // get_rating(name)
    }
    private fun get_rating(name: String?) {
        val database= FirebaseDatabase.getInstance().getReference("review")
        database.orderByChild("satisfy").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (post in snapshot.children) {
                        val result: Review_entity? = post.getValue(Review_entity::class.java)
                        if (result != null) {
                            result?.satisfy = result?.satisfy!! + result.satisfy!!
                            num = result.satisfy!!
                            Toast.makeText(this@HireProfile, num.toString(), Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(this@HireProfile, "No Results", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } else {
                    Toast.makeText(this@HireProfile, "data not found", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HireProfile, "What's wrong with you", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}