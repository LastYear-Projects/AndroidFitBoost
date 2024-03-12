package com.example.firebasetest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var gymList: ArrayList<Gym>
    private lateinit var gymAdapter: GymAdapter

    private lateinit var loaderView : ProgressBar

    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        loaderView = view.findViewById(R.id.loader)
        recyclerView = view.findViewById(R.id.homeRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        gymList = ArrayList()
        // TODO -> fetch all the exercises from the DB and add to the gymList
        fetchExercises()
//        gymList.add(Gym(R.drawable.first, "Your First Workout"))
//        gymList.add(Gym(R.drawable.second, "Your Second Workout"))
//        gymList.add(Gym(R.drawable.third, "Your Third Workout"))

        gymAdapter = GymAdapter(gymList)
        recyclerView.adapter = gymAdapter

        gymAdapter.onItemClick = {
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra("gym", it)
            startActivity(intent)
        }
        return view
    }

    private fun fetchExercises() {
        firestore.collection("exercise")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val title = document.getString("title") ?: ""
                    val imageUrl = document.getString("image") ?: ""
                    val subtitle = document.getString("subtitle") ?: ""
                    gymList.add(Gym(imageUrl, title, subtitle))
                }
                gymAdapter.notifyDataSetChanged()
                loaderView.visibility = View.GONE
            }
            .addOnFailureListener { exception ->
                Log.e("HomeFragment", "error: ${exception.message.toString()}")
            }
    }
}

