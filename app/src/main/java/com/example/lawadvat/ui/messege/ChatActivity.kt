package com.example.lawadvat.ui.messege

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lawadvat.databinding.ActivityChatBinding
import com.example.lawadvat.ui.Variablecontain
import com.example.lawadvat.ui.messege.adapter.MessegeAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatActivity : AppCompatActivity() {
    lateinit var binding:ActivityChatBinding
    val user=FirebaseAuth.getInstance().currentUser?.displayName.toString()
    val user_id=FirebaseAuth.getInstance().currentUser?.uid.toString()
    var num=0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layer_id=intent.getStringExtra(Variablecontain.id)
        val name=intent.getStringExtra(Variablecontain.name)
        binding.sendMessageVector.setOnClickListener{
            val messege=binding.sendMesssage.text.toString()
            if (messege.isNotEmpty())
            {
                val time=LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                msg_send(layer_id,name,messege,time)
            }
            else{
                Toast.makeText(this,"messege not send",Toast.LENGTH_SHORT).show()
            }
            Handler(Looper.getMainLooper()).postDelayed({
                in_it()
                //lawyer_value()
            },100)

        }
    }

    override fun onStart() {
        super.onStart()
        in_it()
    }
    private fun lawyer_value() {
        val layer_id=intent.getStringExtra(Variablecontain.id)
        val name=intent.getStringExtra(Variablecontain.name)
        val database=FirebaseDatabase.getInstance().getReference(layer_id.toString()).child(name.toString())
        val list:ArrayList<Messege?> = ArrayList()
        database.child(user_id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (postSnapshot in snapshot.children) {
                        val result: Messege? = snapshot.getValue(Messege::class.java)
                        list.add(result)
                        recyclerview(list)
                    }
                    }else{
                        Toast.makeText(this@ChatActivity, "data not found", Toast.LENGTH_SHORT)
                            .show()
                    }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ChatActivity,"What's wrong with you", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerview(list: ArrayList<Messege?>) {
        val adapter= MessegeAdapter(this,list)
        binding.chatRecyclerview.setHasFixedSize(true)
        binding.chatRecyclerview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            true
        )
        binding.chatRecyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun in_it() {
        //val layer_id=intent.getStringExtra(Variablecontain.id)
        val name=intent.getStringExtra(Variablecontain.name)

        val database=FirebaseDatabase.getInstance().getReference(user_id)
        database.child(name.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= ArrayList<Messege?>()
                if (snapshot.exists()){
                    num= snapshot.childrenCount.toInt()
                    for (postSnapshot in snapshot.children) {
                        val result: Messege? = postSnapshot.getValue(Messege::class.java)
                        list.add(result)
                        recyclerview(list)
                    }

                }else{
                    Toast.makeText(this@ChatActivity,"data not found",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ChatActivity,"What's wrong with you", Toast.LENGTH_SHORT).show()
            }
    })
    }

    private fun msg_send(layerId: String?, name: String?, messege: String, time: String) {
        val sender_id=intent.getStringExtra(Variablecontain.sender_id)
        val reciver_id=intent.getStringExtra(Variablecontain.reciver_id)
        val database=FirebaseDatabase.getInstance().reference
       val messege=Messege(sender_name = user, sender_id = user_id, reciver_id = layerId,text = messege, time = time)
        database.child(user_id).child(name.toString()).child((num+1).toString()).setValue(messege)
        database.child(sender_id.toString()).child(user).child((num+1).toString()).setValue(messege)
    }
}