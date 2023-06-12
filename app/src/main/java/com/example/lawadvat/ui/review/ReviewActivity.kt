package com.example.lawadvat.ui.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.lawadvat.databinding.ActivityReviewBinding
import com.example.lawadvat.ui.Variablecontain
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewBinding
    val user=FirebaseAuth.getInstance().currentUser?.displayName.toString()
    var num=0
   // var satisfy=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.like.isEnabled=true
        binding.like.setOnClickListener{
            if (binding.like.isChecked){
                val satisfy=1
                in_it(1)
              //  Toast.makeText(this,"like",Toast.LENGTH_SHORT).show()
            }
            else
            {
                in_it(0)
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            in_it(0)
        }, 100)

    }

    private fun in_it(i: Int) {
        val name=intent.getStringExtra(Variablecontain.name)
        val img=intent.getStringExtra(Variablecontain.img)
        binding.reviewName.text=name.toString()
        Glide.with(this).load(img.toString()).into(binding.reviewProfile)
        binding.submitBtn.setOnClickListener {
            val getreview=binding.review.text.toString()

            if (getreview.isEmpty())
            {
                Toast.makeText(this,"Please review",Toast.LENGTH_SHORT).show()
            }
            else{
                /*Handler(Looper.getMainLooper()).postDelayed(
                    {
                        get_rating(name)
                    }
                ,1000)*/
                forward_review(getreview,name,i)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val name=intent.getStringExtra(Variablecontain.name)
        get_rating(name)
    }

    private fun get_rating(name: String?) {
        val database=FirebaseDatabase.getInstance().reference
        database.child("review").child(name.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                     num = snapshot.childrenCount.toInt()

                } else {
                    Toast.makeText(this@ReviewActivity, "data not found", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ReviewActivity, "What's wrong with you", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun forward_review(value: String, name: String?, i: Int) {
        val database=FirebaseDatabase.getInstance().reference
        val database1=FirebaseDatabase.getInstance().getReference("Lawyer")
        val re=Review_entity(user,value,i)
       /* val editor =
            getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()*/
        //database1.child(name.toString()).setValue(review)
        database.child("review").child(name.toString()).child((num + 1).toString()).setValue(re)
            .addOnSuccessListener {
                /*editor.putBoolean("review", true)
                editor.apply()*/
                Toast.makeText(this,"Review successfully submit",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                /*editor.putBoolean("review", false)
                editor.apply()*/
                Toast.makeText(this,"Review failed to submit",Toast.LENGTH_SHORT).show()
            }
        finish()


    }
}