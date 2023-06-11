package com.example.lawadvat.ui.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.lawadvat.databinding.ActivityReviewBinding
import com.example.lawadvat.ui.Variablecontain
import com.example.lawadvat.ui.become.entity.Requirement
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewBinding
    val user=FirebaseAuth.getInstance().currentUser?.displayName.toString()
    var num=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        in_it()
    }

    private fun in_it() {
        val name=intent.getStringExtra(Variablecontain.name)
        val img=intent.getStringExtra(Variablecontain.img)
        binding.reviewName.text=name.toString()
        Glide.with(this).load(img.toString()).into(binding.reviewProfile)
        binding.submitBtn.setOnClickListener {
            val getreview=binding.review.text.toString()

            /* if (!binding.like.isClickable)
             {
                 Toast.makeText(this,"check the reaction button ",Toast.LENGTH_SHORT).show()

             }
             else*/ if (getreview.isEmpty())
            {
                Toast.makeText(this,"Please review",Toast.LENGTH_SHORT).show()
            }/*else if (getreview.length < 5){
                Toast.makeText(this," please write review more than 10 words",Toast.LENGTH_SHORT).show()
            }*/
            else{
                get_list(name ,getreview)
               // forward_review(result, getreview, name)
            }
        }

    }

    private fun get_list(name: String?, getreview: String) {
        val database=FirebaseDatabase.getInstance().getReference("Lawyer")
        database.child(name.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
  //              val list = ArrayList<Requirement?>()
                if (snapshot.exists()) {
                   // num = snapshot.childrenCount.toInt()
                   /* for (postSnapshot in snapshot.children) {*/
                        val result: Requirement? = snapshot.getValue(Requirement::class.java)
                        //list.add(result)
                        forward_review(result,getreview,name)
                    //}

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

    private fun forward_review(result: Requirement?, value: String, name: String?) {
        val database=FirebaseDatabase.getInstance().getReference("Lawyer")
        val review1=Review_entity(user,value)
        val list = ArrayList<Review_entity?>()
       /* var n=num+1
        for (i in 0..n){
            list.add(review1)
        }*/
        val re=Review_entity(user,value)
        val review=Requirement(
            img = result?.img,
            name = result?.name, email = result?.email, phone = result?.phone,
            cnic =result?.cnic, gender = result?.gender,
            country = result?.country, province = result?.province, city = result?.city, office_location = result?.office_location, general__id = result?.general__id, high_court_id = result?.high_court_id, supreme_court_id =result?.supreme_court_id, description =result?.description, experince = result?.experince,
            fee = result?.fee, first_specilize = result?.first_specilize, second_specilize = result?.second_specilize, third_specilize = result?.third_specilize, fourth_specilize = result?.fourth_specilize, llb = result?.llb, llm = result?.llm, bar_of_law = result?.bar_of_law, phd = result?.phd, id = result?.id,
            layer = true)
        val editor =
            getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()

        database.child(name.toString()).child("review").child(user).setValue(re)
            .addOnSuccessListener {
                editor.putBoolean("review", true)
                editor.apply()
                Toast.makeText(this,"Review successfully submit",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                editor.putBoolean("review", false)
                editor.apply()
                Toast.makeText(this,"Review failed to submit",Toast.LENGTH_SHORT).show()
            }

        finish()


    }
}