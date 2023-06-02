package com.example.lawadvat.ui

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach


class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var connectivityReceiver:ConnectivityReceiver
    val firebaseAuth:FirebaseAuth=FirebaseAuth.getInstance()
    val user=firebaseAuth.currentUser?.displayName.toString()
    val databaseRef = FirebaseDatabase.getInstance().reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        connectivityReceiver=ConnectivityReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityReceiver, intentFilter)


    }
    private inner class ConnectivityReceiver : BroadcastReceiver() {
        val mHandler: Handler = Handler()

        @SuppressLint("SetJavaScriptEnabled")
        override fun onReceive(context: Context, intent: Intent) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            val alert=AlertDialog.Builder(context)
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                // Internet connection is available
                mHandler.post {
                    alert.create().hide()
                    if (firebaseAuth.currentUser!=null)
                    {

                            get_profile()

                    }
                    else{
                        val intent=Intent(context,Signup::class.java)
                        startActivity(intent)
                    }
                }
            } else {
                mHandler.post {
                    alert.apply {
                        setIcon(R.mipmap.ic_launcher)
                        setTitle(R.string.app_name)
                        setMessage("No Internet connection\n (Turn on Internet)")
                        setCancelable(false)
                    }
                    alert.create()
                    alert.show()
                    // Toast.makeText(context,"Internet connection failed", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
    fun get_profile()
    {

        databaseRef.child("Lawyer").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                    /*for (postSnapshot in snapshot.children) {*/
                        for (childsnapshot in snapshot.children) {
                            val result: Requirement? =
                                childsnapshot.getValue(Requirement::class.java)
                            if (result?.name == user) {
                                if (result.layer == true) {
                                    val editor =
                                        getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                                    editor.putBoolean("lawyer", true)
                                    editor.apply()
                                    val intent = Intent(this@Splash, MainActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    val editor =
                                        getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                                    editor.putBoolean("lawyer", false)
                                    editor.apply()
                                    val intent = Intent(this@Splash, MainActivity::class.java)
                                    startActivity(intent)
                                }
                            } else {
                                val editor =
                                    getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                                editor.putBoolean("lawyer", false)
                                editor.apply()
                                val intent = Intent(this@Splash, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }

                        }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Splash,error.message,Toast.LENGTH_SHORT).show()
            }

        })

    }

}