package com.example.lawadvat.ui.search

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lawadvat.R
import com.example.lawadvat.databinding.ActivitySearchBinding
import com.example.lawadvat.ui.become.entity.Requirement
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Locale

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    val permissionId = 100
    var field = "name"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Search"

        binding.searchBar.setOnClickListener {
            binding.searchBar.isIconified = false;
        }
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Perform the search when the user submits the query
                if (query.isNotEmpty()) {
                    performSearch(query)
                }
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                // Perform the search as the user types (optional)
                if (newText.isNotEmpty()) {
                    performSearch(newText)
                }
                return false
            }
        })
        binding.filterSearch.setOnClickListener {
            popup_filter()
        }

    }

    private fun popup_filter() {
        val editor =
            getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val popmenu=PopupMenu(this@SearchActivity,binding.filterSearch)
        popmenu.menuInflater.inflate(R.menu.search_popup,popmenu.menu)
        popmenu.setOnMenuItemClickListener { item ->
            when (item!!.itemId) {
                R.id.search_by_name -> {

                    editor.putString("search_name", "name")
                    editor.apply()
                }

                R.id.search_by_location -> {
                    editor.putString("search_name", "office_location")
                    getLocation()
                    editor.apply()

                }
                R.id.search_by_city -> {
                    editor.putString("search_name", "city")
                    editor.apply()

                }
                R.id.search_by_email -> {
                    editor.putString("search_name", "email")
                    editor.apply()
                }

                R.id.search_by_phone -> {
                    editor.putString("search_name", "phone")
                    editor.apply()
                }

                R.id.search_by_general_id -> {
                    editor.putString("search_name", "general__id")
                    editor.apply()

                }

                R.id.search_by_highcourt_id -> {
                    editor.putString("search_name", "high_court_id")
                    editor.apply()

                }

                R.id.search_by_supreme_id -> {
                    editor.putString("search_name", "supreme_court_id")
                    editor.apply()
                }



                R.id.search_by_supreme_id -> {
                    editor.putString("search_name", "supreme_court_id")
                    editor.apply()
                }
            }
            true
        }
        popmenu.show()
    }

    private fun performSearch(query: String) {
        val searchResults: ArrayList<Requirement?> = ArrayList()
        val value =
            getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        val field_name=value.getString("search_name",null)
        val databaseReference = FirebaseDatabase.getInstance().getReference("Lawyer")
        val searchQuery =
            databaseReference.orderByChild(field_name.toString()).startAt(query).endAt(query + "\uf8ff")
        searchQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Handle the search results here
                searchResults.clear()
                for (snapshot in dataSnapshot.children) {
                    val result: Requirement? = snapshot.getValue(Requirement::class.java)
                    if (result != null) {
                        searchResults.add(result)

                    } else {
                        Toast.makeText(this@SearchActivity, "No Results", Toast.LENGTH_SHORT).show()
                    }

                    recyclerview(searchResults)
                }


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors
            }
        })


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerview(searchResults: ArrayList<Requirement?>) {
        val adapter= Search_Adapter(this, searchResults)
        binding.listSearch.setHasFixedSize(true)
        binding.listSearch.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.listSearch.adapter = adapter
        adapter.notifyDataSetChanged()
        binding.searchBar.setOnCloseListener {
            adapter.list.clear()
            false
        }
    }
    @SuppressLint("SetTextI18n")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                       this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ){
                    return
                }
                /*{
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }*/
                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    /*
                                        Handler(Looper.getMainLooper()).postDelayed({
                                            //val mainIntent = Intent(this, Menu::class.java)
                                            //startActivity(mainIntent)
                                            finish()
                                        }, task.isComplete as Long)
                    */
                    if (location != null) {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val list: List<Address> =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>
                        binding.apply {
                            /*itemCountry.setText( list[0].countryName.toString())
                            itemProvince.setText(list[0].adminArea.toString())
                            itemCity.setText("\n${list[0].locality}")
                            itemLocation.setText("\n${list[0].getAddressLine(0).trimStart()}")*/
                            performSearch("\n${list[0].getAddressLine(0).trimStart()}")
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }
}