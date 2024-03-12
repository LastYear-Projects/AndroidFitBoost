package com.example.firebasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MyWorkoutsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutList: ArrayList<Workout>
    private lateinit var workoutAdapter: WorkoutAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_workouts2)
    }
}