package com.example.lawadvat.ui.messege

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentNotificationsBinding
import com.example.lawadvat.ui.messege.adapter.ChatmainAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MessegeFragment : Fragment() {
    lateinit var binding:FragmentNotificationsBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white)
       /* val notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)*/
        binding = FragmentNotificationsBinding.inflate(layoutInflater)
        in_it()
        return binding.root
    }

    private fun in_it() {
        //val name=intent.getStringExtra(Variablecontain.name)
        val user_id=FirebaseAuth.getInstance().currentUser?.uid.toString()
        val database= FirebaseDatabase.getInstance().getReference(user_id)
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= ArrayList<Messege?>()
                if (snapshot.exists()){
                    for (postSnapshot in snapshot.children) {
                        val result: Messege? = postSnapshot.children.first().getValue(Messege::class.java)
                        list.add(result)
                        recyclerview(list)
                    }

                }else{
                    binding.showAnswerChat.isVisible=true
                    Toast.makeText(requireContext(),"data not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"What's wrong with you", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerview(list: ArrayList<Messege?>) {
        val adapter= ChatmainAdapter(requireContext(),list)
        binding.chatuserRecyclerview.setHasFixedSize(true)
        binding.chatuserRecyclerview.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            true
        )
        binding.chatuserRecyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }


}