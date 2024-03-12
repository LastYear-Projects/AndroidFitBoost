package com.example.firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyWorkoutsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutList: ArrayList<Workout>
    private lateinit var workoutAdapter: WorkoutAdapter
//    private lateinit var loaderView: ProgressBar
    private lateinit var auth: FirebaseAuth

    private val dataBase = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_workouts2)

//        loaderView.visibility = View.GONE
        recyclerView = findViewById(R.id.rv_MyWorkouts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        workoutList = ArrayList()
        workoutAdapter = WorkoutAdapter(workoutList)
        fetchMyWorkouts();



    }




//        loaderView = view.findViewById(R.id.loader)
//        recyclerView = view.findViewById(R.id.homeRecyclerView)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//
//        workoutList = ArrayList()
//        // TODO -> fetch all the exercises from the DB and add to the gymList
//        fetchExercises();
//
//        loaderView.visibility = View.GONE
//
////        gymList.add(Gym(R.drawable.first, "Your First Workout"))
////        gymList.add(Gym(R.drawable.second, "Your Second Workout"))
////        gymList.add(Gym(R.drawable.third, "Your Third Workout"))
//
//        workoutAdapter = WorkoutAdapter(workoutList)
//        recyclerView.adapter = workoutAdapter
//
//        workoutAdapter.onItemClick = {
//            val intent = Intent(activity, DetailsActivity::class.java)
//            intent.putExtra("gym", it)
//            startActivity(intent)
//        }
//        return view
//    }

    private fun fetchMyWorkouts() {
        val currentUser = auth.currentUser
        currentUser?.email?.let { Log.e("currentUser", it) }
        dataBase.collection("exercise")
            .get()
            .addOnSuccessListener { result ->
                Log.e("Result", result.size().toString());
                for (document in result) {
                    if(document.getString("owner") != currentUser?.email) continue
                    val title = document.getString("title") ?: ""
                    val imageUrl = document.getString("image") ?: ""
                    val subtitle = document.getString("subtitle") ?: ""
                    workoutList.add(Workout(imageUrl, title, subtitle))
                }
                Log.e("myWorkOuts", workoutList.toString())
                recyclerView.adapter = WorkoutAdapter(workoutList);
                workoutAdapter.notifyDataSetChanged()


            }
            .addOnFailureListener { exception ->
                Log.e("MyWorkOuts2", "error: ${exception.message.toString()}")
                Toast.makeText(
                    this,
                    "error: ${exception.message.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}