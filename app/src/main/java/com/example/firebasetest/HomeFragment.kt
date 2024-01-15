package com.example.firebasetest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var gymList: ArrayList<Gym>
    private lateinit var gymAdapter: GymAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.homeRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        gymList = ArrayList()
        // TODO -> fetch all the exercises from the DB and add to the gymList
        gymList.add(Gym(R.drawable.first, "Your First Workout"))
        gymList.add(Gym(R.drawable.second, "Your Second Workout"))
        gymList.add(Gym(R.drawable.third, "Your Third Workout"))

        gymAdapter = GymAdapter(gymList)
        recyclerView.adapter = gymAdapter

        gymAdapter.onItemClick = {
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra("gym", it)
            startActivity(intent)
        }

        return view
    }
}
