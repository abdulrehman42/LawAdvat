package com.example.lawadvat.ui.setting

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.lawadvat.MainActivity
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentSettingBinding
import com.example.lawadvat.ui.appointment.HomeFragment
import com.example.lawadvat.ui.signup.Signup
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Setting : Fragment() {
    private lateinit var binding:FragmentSettingBinding
    private lateinit var viewModel: SettingViewModel
    lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSettingBinding.inflate(inflater)
        requireActivity()?.actionBar?.hide()
        viewModel = ViewModelProvider(this)[SettingViewModel::class.java]
        auth=FirebaseAuth.getInstance()
        binding.displayNameSetting.text=auth.currentUser?.displayName
        Glide.with(this)
            .load(auth.currentUser?.photoUrl)
            .into(binding.profileSetting)
        binding.logout.setOnClickListener{
            auth.signOut()
            val intent=Intent(requireActivity(),MainActivity::class.java)
            startActivity(intent)
        }
        binding.join.setOnClickListener{
            if (auth.currentUser!=null)
            {
                Snackbar.make(requireView(),"Already Signin",Snackbar.LENGTH_SHORT).show()
            }
            else
            {
                val intent=Intent(requireActivity(),Signup::class.java)
                startActivity(intent)
            }
        }
        binding.become.setOnClickListener{
            if (auth.currentUser!=null)
            {
                Snackbar.make(requireView(),"we working on it",Snackbar.LENGTH_SHORT).show()
            }
            else
            {
                val intent=Intent(requireActivity(),Signup::class.java)
                startActivity(intent)
            }
        }
        binding.complain.setOnClickListener{
            if (auth.currentUser!=null)
            {
                Snackbar.make(requireView(),"we working on it",Snackbar.LENGTH_SHORT).show()
            }
            else
            {
                val intent=Intent(requireActivity(),Signup::class.java)
                startActivity(intent)
            }
        }
        return binding.root
    }





}