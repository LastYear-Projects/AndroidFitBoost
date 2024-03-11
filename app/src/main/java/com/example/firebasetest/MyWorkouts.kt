package com.example.firebasetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyWorkoutsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var gymList: ArrayList<Gym>
    private lateinit var gymAdapter: GymAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_workouts) // Ensure you have the corresponding layout

        // Initialize your RecyclerView and other components here, similar to how it was done in the fragment
        recyclerView = findViewById(R.id.homeRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Your logic to fetch and display the data
    }
}
