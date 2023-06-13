package com.example.lawadvat.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.lawadvat.R
import com.example.lawadvat.databinding.ActivityDisputeBinding
import com.example.lawadvat.ui.Variablecontain
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Dispute : AppCompatActivity() {
    lateinit var binding:ActivityDisputeBinding
    val user=FirebaseAuth.getInstance().currentUser?.displayName.toString()
    val user_email=FirebaseAuth.getInstance().currentUser?.email.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDisputeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Dispute"
        in_it()
    }

    private fun in_it() {
        val value=binding.repotText.text.toString()
        val name=intent.getStringExtra(Variablecontain.name)
        val email=intent.getStringExtra(Variablecontain.email)
        val img=intent.getStringExtra(Variablecontain.img)
        Glide.with(this).load(img).into(binding.reportProfile)
        binding.reportBtn.setOnClickListener {
        if (value.isNotEmpty())    {
            insert_complain(value,name,email,img,user,user_email)
        }else{
            Toast.makeText(this,"write dispute",Toast.LENGTH_SHORT).show()
        }

        }
    }

    private fun insert_complain(value:String,name: String?, email: String?, img: String?, user: String, userEmail: String) {
        val database=FirebaseDatabase.getInstance().reference
        val dispute=Dispute_entity(name,value,email,img,user,user_email)
        database.child("Dispute").setValue(dispute)
            .addOnSuccessListener {
                Toast.makeText(this,"Your Dispute successfully submit",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(this,"Your Dispute failed to submit",Toast.LENGTH_SHORT).show()

            }
        finish()
    }
}