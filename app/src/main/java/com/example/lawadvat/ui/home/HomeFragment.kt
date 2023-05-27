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
import com.example.lawadvat.ui.home.adapter.ListItem
import com.example.lawadvat.ui.home.adapter.SpecilizeRecyclerviw
import com.example.lawadvat.ui.signup.Signup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

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
            Toast.makeText(requireContext(), user.displayName, Toast.LENGTH_LONG).show()

        } else {
            val intent=Intent(context,Signup::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun update_ui(user: FirebaseUser) {
        slider()
        recyclerview()
        Glide.with(this)
            .load(user.photoUrl)
            .into(binding.profile)
        binding.name.text="WELCOME BACK,\n\t\t "+ user.displayName
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

    private fun slider() {
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel("https://bit.ly/2YoJ77H"))
        imageList.add(SlideModel("https://bit.ly/2BteuF2"))
        imageList.add(SlideModel("https://bit.ly/3fLJf72"))
        val imageSlider = requireActivity().findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
    }

}