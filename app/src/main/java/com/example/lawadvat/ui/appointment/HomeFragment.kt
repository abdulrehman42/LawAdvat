package com.example.lawadvat.ui.appointment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.lawadvat.databinding.FragmentHomeBinding
import com.example.lawadvat.ui.signup.Signup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


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
        Glide.with(this)
            .load(user.photoUrl)
            .into(binding.profile)
        binding.name.text="WELCOME BACK,\n\t\t "+ user.displayName
    }

    /*private fun sign_process() {
        googleoption= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail().build()
        googleclient= GoogleSignIn.getClient(requireActivity(),googleoption)
        val account: GoogleSignInAccount? =GoogleSignIn.getLastSignedInAccount(requireActivity())
        if (account!=null)
        {
            Toast.makeText(requireContext(),account.displayName, Toast.LENGTH_LONG).show()

        }
        else
        {
            Toast.makeText(requireContext(),"Oop", Toast.LENGTH_LONG).show()

        }
    }*/
    /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
}