package com.example.lawadvat.ui.messege

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import com.example.lawadvat.ui.mail.SendMailTask
import com.example.lawadvat.ui.messege.adapter.MessegeAdapter
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatBinding
    private var mInterstitialAd: InterstitialAd? = null
    val user = FirebaseAuth.getInstance().currentUser?.displayName.toString()
    val user_email = FirebaseAuth.getInstance().currentUser?.email.toString()
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
        ad_load()
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
        val email=intent.getStringExtra(Variablecontain.email)
        binding.sendMessageVector.setOnClickListener {
            val messege = binding.sendMesssage.text.toString()
            if (messege.isNotEmpty()) {
                if (layer_id?.isNotEmpty() == true) {
                    if (reciver_img != null) {
                        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                        msg_send(layer_id, name, reciver_img, messege, time,email)
                    } else {
                        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                        msg_send(layer_id, name, img, messege, time, email)
                    }


                } else {
                    if (img != null) {
                        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                        msg_send(reciver_id, name, img, messege, time, email)
                    } else {
                        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                        msg_send(reciver_id, name, reciver_img, messege, time, email)
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

    private fun ad_load() {
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError?.toString()!!)
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d(TAG, "Ad dismissed fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.")
            }

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
                    Toast.makeText(this@ChatActivity, "data ", Toast.LENGTH_SHORT).show()
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
        time: String,
        email: String?
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
            time = time,
            email = email
        )
        //      val reciver_side=Messege(sender_name = name, sender_id = user_id, reciver_id = layerId,text = messege, time = time)
        if (sender_id!=null && sender_id!=user_id)
        {
            database.child(user_id).child(name.toString()).child((num + 1).toString())
                .setValue(current_user)
            database.child(sender_id.toString()).child(user).child((num + 1).toString())
                .setValue(current_user)
            val msg="Hi $user,\n $name sent you message \n" +
                    " open the Lawadvat App"
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
            send_Email(email.toString(),
                msg
            )
        }else
        {
            val current_user1 = Messege(
                sender_name = user,
                sender_image = user_pic,
                reciver_image = img,
                reciver_name = name,
                sender_id = user_id,
                reciver_id = layerId,
                text = messege,
                time = time,
                email = user_email
            )
            database.child(user_id).child(name.toString()).child((num + 1).toString())
                .setValue(current_user1)
            database.child(layerId.toString()).child(user).child((num + 1).toString())
                .setValue(current_user1)
            val msg="Hi $name,\n $user sent you message \n" +
                    " open the Lawadvat App"
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
            send_Email(email.toString(),
                msg
            )
        }

    }
    private fun send_Email(
        Receiver: String,
        Message: String
    ) {
        Thread {
            try {

                val sm = SendMailTask(
                    this@ChatActivity,
                    "abdulrehman4241566@gmail.com",
                    Receiver,
                    "Message",
                    Message,
                    "dtzxlvtlubqpigny"
                )
                sm.sendMail()
                /*Toast.makeText(this, "Mail send.", Toast.LENGTH_SHORT).show()*/
            } catch (e: Exception) {
                Log.e("SendMail", e.message, e)
            }
        }.start()
    }


}