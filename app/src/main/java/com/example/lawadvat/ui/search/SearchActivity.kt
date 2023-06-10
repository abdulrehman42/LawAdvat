package com.example.lawadvat.ui.search

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lawadvat.databinding.ActivitySearchBinding
import com.example.lawadvat.ui.become.entity.Requirement
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    val searchResults: ArrayList<Requirement?> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Search"
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Perform the search when the user submits the query
                performSearch(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                // Perform the search as the user types (optional)
                performSearch(newText)
                return false
            }
        })

    }

    private fun performSearch(query: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Lawyer")
        val searchQuery =
            databaseReference.orderByChild("name").startAt(query).endAt(query + "\uf8ff")
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
                }
                recyclerview(searchResults)

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
            LinearLayoutManager.HORIZONTAL,
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