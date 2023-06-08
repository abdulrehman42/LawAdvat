package com.example.lawadvat.ui.messege

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.lawadvat.R
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
    lateinit var binding: ActivityChatBinding
    val user = FirebaseAuth.getInstance().currentUser?.displayName.toString()
    val user_id = FirebaseAuth.getInstance().currentUser?.uid.toString()
    val user_pic = FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
    var num = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_actionbar)
        //getSupportActionBar().setElevation(0);
        //getSupportActionBar().setElevation(0);
        val view: View = supportActionBar!!.customView
        val nametext = view.findViewById<TextView>(R.id.text_actionbar)
        val actionimg = view.findViewById<ImageView>(R.id.image_actionbar)
        /*if (img!=null)
        {
            nametext.text = name.toString()
            Glide.with(this).load(img).into(actionimg)

        }else{*/
        val layer_id = intent.getStringExtra(Variablecontain.id)
        val name = intent.getStringExtra(Variablecontain.name)
        val img = intent.getStringExtra(Variablecontain.img2)
        val reciver_img = intent.getStringExtra(Variablecontain.reciver_img)
        nametext.text = name.toString()
        if (reciver_img==user_pic){
            if (img!=null)
            {
                Glide.with(this).load(img).into(actionimg)
            }
            else{
                Glide.with(this).load(reciver_img).into(actionimg)
            }

        }else{
            if (img!=null)
            {
                Glide.with(this).load(img).into(actionimg)
            }
            else{
                Glide.with(this).load(reciver_img).into(actionimg)
            }
        }
        val reciver_id = intent.getStringExtra(Variablecontain.reciver_id)
        binding.sendMessageVector.setOnClickListener {
            val messege = binding.sendMesssage.text.toString()
            if (messege.isNotEmpty()) {
                if (layer_id?.isNotEmpty() == true) {
                    if (reciver_img != null) {
                        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                        msg_send(layer_id, name, reciver_img, messege, time)
                    } else {
                        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                        msg_send(layer_id, name, img, messege, time)
                    }


                } else {
                    if (img != null) {
                        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                        msg_send(reciver_id, name, img, messege, time)
                    } else {
                        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                        msg_send(reciver_id, name, reciver_img, messege, time)
                    }

                }
            } else {
                Toast.makeText(this, "messege not send", Toast.LENGTH_SHORT).show()
            }
            binding.sendMesssage.setText("")
            Handler(Looper.getMainLooper()).postDelayed({
                in_it()

            }, 100)

        }
    }

    override fun onStart() {
        super.onStart()
        in_it()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerview(list: ArrayList<Messege?>) {
        val adapter = MessegeAdapter(this, list)
        binding.chatRecyclerview.setHasFixedSize(true)
        binding.chatRecyclerview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.chatRecyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun in_it() {
        //val layer_id=intent.getStringExtra(Variablecontain.id)
        val name = intent.getStringExtra(Variablecontain.name)
        val database = FirebaseDatabase.getInstance().getReference(user_id)
        database.child(name.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Messege?>()

                if (snapshot.exists()) {
                    num = snapshot.childrenCount.toInt()
                    for (postSnapshot in snapshot.children) {
                        val result: Messege? = postSnapshot.getValue(Messege::class.java)
                        list.add(result)
                        recyclerview(list)
                    }

                } else {
                    Toast.makeText(this@ChatActivity, "data not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ChatActivity, "What's wrong with you", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun msg_send(
        layerId: String?,
        name: String?,
        img: String?,
        messege: String,
        time: String
    ) {
        val sender_id = intent.getStringExtra(Variablecontain.sender_id)
        val database = FirebaseDatabase.getInstance().reference
        val current_user = Messege(
            sender_name = user,
            sender_image = user_pic,
            reciver_image = img,
            reciver_name = name,
            sender_id = user_id,
            reciver_id = layerId,
            text = messege,
            time = time
        )
        //      val reciver_side=Messege(sender_name = name, sender_id = user_id, reciver_id = layerId,text = messege, time = time)
        if (sender_id!=null && sender_id!=user_id)
        {
            database.child(user_id).child(name.toString()).child((num + 1).toString())
                .setValue(current_user)
            database.child(sender_id.toString()).child(user).child((num + 1).toString())
                .setValue(current_user)
        }else
        {
            database.child(user_id).child(name.toString()).child((num + 1).toString())
                .setValue(current_user)
            database.child(layerId.toString()).child(user).child((num + 1).toString())
                .setValue(current_user)
        }

    }
}