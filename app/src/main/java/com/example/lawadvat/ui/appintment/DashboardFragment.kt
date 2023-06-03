package com.example.lawadvat.ui.appintment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentDashboardBinding
import com.example.lawadvat.ui.appintment.adapter.AppointmentAdpter
import com.example.lawadvat.ui.appintment.appointment.Appoint
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DashboardFragment : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    val firebaseAuth:FirebaseAuth=FirebaseAuth.getInstance()
    val user=firebaseAuth.currentUser?.displayName.toString()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white);
        val dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        binding= FragmentDashboardBinding.inflate(layoutInflater)
        binding.swipeRefreshLayout.setOnRefreshListener {
            in_it()
            binding.swipeRefreshLayout.isRefreshing = false
            Snackbar.make(requireView(),"Refresh", Snackbar.LENGTH_SHORT).show()
        }

        in_it()
        return binding.root
    }

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        in_it(context)
    }*/
    private fun in_it() {
        val database=FirebaseDatabase.getInstance().reference
        val list:ArrayList<Appoint?> = ArrayList()
        database.child(user).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                for (postSnapshot in snapshot.children) {
                        val result: Appoint? =postSnapshot.getValue(Appoint::class.java)
                        list.add(result)
                }
                    recyclerview(list)
            }else{
                    binding.result.isVisible=true
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"What's wrong with you", Toast.LENGTH_SHORT).show()
            }

        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerview(list: ArrayList<Appoint?>) {
        if (!isAdded)
        {
            Toast.makeText(activity,"not added",Toast.LENGTH_SHORT).show()
        }
        val adapter=AppointmentAdpter(requireActivity(),list)
        binding.recyclerviewAppointment.setHasFixedSize(true)
        binding.recyclerviewAppointment.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            true
        )
        binding.recyclerviewAppointment.adapter = adapter
        adapter.notifyDataSetChanged()
    }



}