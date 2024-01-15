package com.example.firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomePageTestActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var gymList: ArrayList<Gym>
    private lateinit var gymAdapter: GymAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_test)

        recyclerView = findViewById(R.id.homeRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        gymList = ArrayList()

        gymList.add(Gym(R.drawable.first, "Your First Workout"))
        gymList.add(Gym(R.drawable.second, "Your Second Workout"))
        gymList.add(Gym(R.drawable.third, "Your Third Workout"))

        gymAdapter = GymAdapter(gymList)
        recyclerView.adapter = gymAdapter

        gymAdapter.onItemClick = {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("gym", it)
            startActivity(intent)
        }

    }
}