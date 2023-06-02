package com.example.lawadvat

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lawadvat.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navController.context.getColor(R.color.black)
        val prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        val check = prefs.getBoolean("lawyer",false)
        val v=intent.getBooleanExtra("check",false)
        if (check)
        {
            navController.navigate(R.id.navigation_lawyer)
            val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_lawyer,R.id.navigation_lawyer, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_setting))
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        } else if (v)
        {
            navController.navigate(R.id.navigation_lawyer)
            val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_lawyer,R.id.navigation_lawyer, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_setting))
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
        else
        {
            val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home,R.id.navigation_lawyer, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_setting))
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}