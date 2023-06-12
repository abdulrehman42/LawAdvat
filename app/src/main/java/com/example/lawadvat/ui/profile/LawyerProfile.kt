package com.example.lawadvat.ui.profile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentLawyerProfileBinding
import com.example.lawadvat.ui.become.entity.Requirement
import com.example.lawadvat.ui.home.adapter.ListItem
import com.example.lawadvat.ui.home.adapter.SpecilizeRecyclerviw
import com.example.lawadvat.ui.review.Review_entity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LawyerProfile : Fragment() {
    lateinit var binding: FragmentLawyerProfileBinding
    private lateinit var database: DatabaseReference
    var review=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLawyerProfileBinding.inflate(layoutInflater)
        requireActivity().window?.statusBarColor = requireActivity().getColor(R.color.white)
        requireActivity().actionBar?.isShowing
        requireActivity().actionBar?.title="Profile"
        //lets_start(requireContext())
       /* Handler(Looper.getMainLooper()).postDelayed({
            lets_start(requireContext())
        }, 100)*/
get_review()

        return binding.root
    }

    private fun get_review() {
        val user = FirebaseAuth.getInstance().currentUser?.displayName.toString()
        val database=FirebaseDatabase.getInstance().getReference("review")
        database.child(user).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list=ArrayList<Review_entity?>()
                if (snapshot.exists())
                {
                    review=snapshot.childrenCount.toInt()
                    for (postshot in snapshot.children){
                        val review_result:Review_entity?=postshot.getValue(Review_entity::class.java)
                        list.add(review_result)
                    }
                    recyclerview_review(list)
                }
                /*else{
                    binding.noReview.isVisible=true
                }*/
                lets_start(requireContext())
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerview_review(list: ArrayList<Review_entity?>) {
        val adapter= Review_Adapter(requireContext(),list)
        binding.recyclerReview.setHasFixedSize(true)
        binding.recyclerReview.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerReview.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    private fun lets_start(context: Context) {
        val user = FirebaseAuth.getInstance().currentUser?.displayName.toString()
        database = Firebase.database.reference
        database.child("Lawyer").child(user).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               /* for (postSnapshot in snapshot.children) {*/
                    if (snapshot.exists()){
                        val result: Requirement? =
                            snapshot.getValue(Requirement::class.java)
                        if (result?.layer == true) {
                            in_it(result,context)

                        }
                    }else{
                        Toast.makeText(requireContext(),"Not found lawyer data", Toast.LENGTH_SHORT).show()
                    }
                //}

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    @SuppressLint("SetTextI18n")
    fun in_it(array: Requirement?, view: Context) {
        if (array != null) {
            Glide.with(view)
                .load(array.img)
                .into(binding.profileLawyerImage)
            binding.profileLawyerName.text = array.name.toString()
            binding.review.text="($review)"
            binding.location.text = array.office_location.toString()
            binding.experience.text = "Experience:"+array.experince.toString()
            binding.first.text = "\u25BA"+array.first_specilize.toString()
            binding.second.text = "\u25BA"+array.second_specilize.toString()
            binding.third.text = "\u25BA"+array.third_specilize.toString()
            binding.fourth.text = "\u25BA"+array.fourth_specilize.toString()
            binding.firstEd.text ="\u25BA"+ array.llb.toString()
            binding.high.text=array.first_specilize.toString()
            binding.fee.text=array.fee.toString()+"PKR"
            binding.overview.text=array.description.toString()

            if (array.llm.equals("null")) {
                binding.secondEd.isVisible = false
            } else if (array.bar_of_law.equals("null")) {
                binding.thirdEd.isVisible = false
            } else if (array.phd.equals("null")) {
                binding.fourthEd.isVisible = false
            } else {
                binding.thirdEd.isInvisible = false
                binding.thirdEd.text = "\u25BA"+array.bar_of_law.toString()
                binding.secondEd.isInvisible = false
                binding.secondEd.text = "\u25BA"+array.llm.toString()
                binding.fourthEd.isInvisible = false
                binding.fourthEd.text = "\u25BA"+array.phd.toString()
            }
        } else {
            val editor = requireActivity().getSharedPreferences("MY_PREFS_NAME", AppCompatActivity.MODE_PRIVATE).edit()
            editor.putBoolean("lawyer", false)
            editor.apply()
            Toast.makeText(requireContext(), "no result", Toast.LENGTH_SHORT).show()
        }

    }

/*    override fun onStart() {
        super.onStart()
        get_review()
    }*/
}