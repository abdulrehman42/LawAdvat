package com.example.lawadvat.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lawadvat.MainActivity
import com.example.lawadvat.R
import com.example.lawadvat.databinding.ActivitySplashBinding
import com.example.lawadvat.ui.become.entity.Requirement
import com.example.lawadvat.ui.profile.LawyerProfile
import com.example.lawadvat.ui.setting.Setting
import com.example.lawadvat.ui.signup.Signup
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.snapshots


class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    val firebaseAuth:FirebaseAuth=FirebaseAuth.getInstance()
    val user=firebaseAuth.currentUser?.displayName.toString()
    val databaseRef = FirebaseDatabase.getInstance().reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        if (firebaseAuth.currentUser!=null)

        {
            if (databaseRef.child(user).equals(user))
            {
                get_profile()
            }
                else
            {
                  val editor =
            getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
        editor.putBoolean("lawyer", false)
        editor.apply()
        val intent = Intent(this@Splash, MainActivity::class.java)
        startActivity(intent)
            }

        }
        else{
            val intent=Intent(this,Signup::class.java)
            startActivity(intent)
        }


    }
    fun get_profile()
    {

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                    for (postSnapshot in snapshot.children) {
                        val result: Requirement? =
                            postSnapshot.child(user).getValue(Requirement::class.java)

                            if (result?.layer == true) {
                                val editor =
                                    getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                                editor.putBoolean("lawyer", true)
                                editor.apply()
                                val intent = Intent(this@Splash, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                        }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

}