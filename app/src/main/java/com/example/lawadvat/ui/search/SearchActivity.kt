package com.example.lawadvat.ui.search

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lawadvat.R
import com.example.lawadvat.databinding.ActivitySearchBinding
import com.example.lawadvat.ui.become.entity.Requirement
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    var field="name"
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Search"
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
        val popmenu=PopupMenu(this@SearchActivity,binding.filterSearch)
        popmenu.menuInflater.inflate(R.menu.search_popup,popmenu.menu)
        popmenu.setOnMenuItemClickListener { item ->
            when (item!!.itemId) {
                R.id.search_by_name -> {

                    editor.putString("search_name", "name")
                    editor.apply()
                }

                R.id.search_by_location -> {
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

}