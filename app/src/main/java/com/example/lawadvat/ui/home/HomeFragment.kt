package com.example.lawadvat.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentHomeBinding
import com.example.lawadvat.ui.become.entity.Requirement
import com.example.lawadvat.ui.home.adapter.ListItem
import com.example.lawadvat.ui.home.adapter.SpecilizeRecyclerviw
import com.example.lawadvat.ui.home.adapter.lawyers.LawyerAdapter
import com.example.lawadvat.ui.signup.Signup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    val list:ArrayList<Requirement?> = ArrayList()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white);
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            update_ui(user)
            get_result()
            Toast.makeText(requireContext(), user.displayName, Toast.LENGTH_LONG).show()

        } else {
            val intent=Intent(context,Signup::class.java)
            startActivity(intent)
        }
    }

    private fun get_result() {
        database = FirebaseDatabase.getInstance().getReference("Lawyer")
        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    val result: Requirement? =postSnapshot.getValue(Requirement::class.java)!!
                    list.add(result)
                    recyclerview_hire(list)
                }
            }

            override fun onCancelled(error: DatabaseError) {
               Toast.makeText(requireContext(),"What's wrong with you",Toast.LENGTH_SHORT).show()
            }

        })
    }

    @SuppressLint("SetTextI18n")
    private fun update_ui(user: FirebaseUser) {
        slider()
        recyclerview()
        Glide.with(this)
            .load(user.photoUrl)
            .into(binding.profile)
        binding.name.text="WELCOME BACK,\n\t\t\t"+ user.displayName
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerview() {
        val adapter=SpecilizeRecyclerviw(requireContext(),ListItem.getlist())
        binding.specificationRecyclerview.setHasFixedSize(true)
        binding.specificationRecyclerview.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            true
        )
        binding.specificationRecyclerview.adapter = adapter

        adapter.notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerview_hire(list: ArrayList<Requirement?>) {
        val adapter=LawyerAdapter(requireContext(),list)
        binding.lawyerRecyclerview.setHasFixedSize(true)
        binding.lawyerRecyclerview.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            true
        )
        binding.lawyerRecyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun slider() {
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel("https://img.freepik.com/free-vector/justice-typographic-header-court-worker-stand-justice-law-judge-traditional-black-robe-hearing-case-sentencing-judgement-punishment-idea-isolated-flat-vector-illustration_613284-2733.jpg?w=996&t=st=1686256652~exp=1686257252~hmac=895752e35ed0961b9e553cbdbf83cfbe0837700e6ef1c2c71abc273f6079a042"))
        imageList.add(SlideModel("https://img.freepik.com/free-vector/judge-online-service-platform-court-worker-stand-justice-law-judge-traditional-black-robe-hearing-case-sentencing-isolated-flat-vector-illustration_613284-114.jpg?w=740&t=st=1686256749~exp=1686257349~hmac=dba23d87ef79b32f41959bdcd6aa3963db3f2c4ae1628ba0f58d114dfa9b7bbe"))
        imageList.add(SlideModel("https://img.freepik.com/free-vector/prosecutor-concept-illustration_114360-10019.jpg?w=740&t=st=1686256879~exp=1686257479~hmac=67f0ed01f37aaa9f99da4870e6c884be2a4f5ce1518e6bda50abbb3b01f7c3b0"))
        imageList.add(SlideModel("https://img.freepik.com/free-vector/legal-advisers-concept-illustration_114360-16439.jpg?w=740&t=st=1686256733~exp=1686257333~hmac=8243b9712bffe9e36fa52a9ec0d3f986a0bf633bb75cb0704fa6b6ed4d2d1411"))
        imageList.add(SlideModel("https://img.freepik.com/free-vector/messages-concept-illustration_114360-583.jpg?w=740&t=st=1686257065~exp=1686257665~hmac=0c86bd2c8c350fe217acb5ce67deb9b53cbc138df9725d2e9b95841f12fbb271"))
        imageList.add(SlideModel("https://img.freepik.com/free-vector/appointment-booking-with-smartphone-man_23-2148555982.jpg?w=740&t=st=1686257165~exp=1686257765~hmac=cd0423cdd16201c68e240b2f4453614fbb94c9d6db4f5d6804359ab94839beab"))
        imageList.add(SlideModel("https://img.freepik.com/free-vector/personal-site-concept-illustration_114360-2577.jpg?w=740&t=st=1686257305~exp=1686257905~hmac=c1ee7b0657de32318bb35ad56eb2d248e064398470845102f952a7b22db1ffcd"))
        imageList.add(SlideModel("https://img.freepik.com/free-vector/lawyer-concept-illustration_114360-2310.jpg?w=740&t=st=1686256959~exp=1686257559~hmac=b6a900d70a3f7f83c344b48990814e5709cda6b1a058819c8a38dc6c0837dbfe"))
        val imageSlider = requireActivity().findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
    }

}