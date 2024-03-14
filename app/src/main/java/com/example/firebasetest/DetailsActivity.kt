package com.example.firebasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso


class DetailsActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore

    private lateinit var exerciseRecyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseInWorkOutAdapter
    private lateinit var exercisesList: ArrayList<ExerciseInWorkOut>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val gym = intent.getParcelableExtra<Workout>("gym")
        exerciseRecyclerView = findViewById(R.id.exerciseRecyclerView)
//        exerciseAdapter = ExerciseInWorkOutAdapter(emptyList()) // Initialize with an empty list
        exercisesList = ArrayList()
//        exerciseRecyclerView.adapter = exerciseAdapter
        exerciseRecyclerView.layoutManager = LinearLayoutManager(this)

        firestore = FirebaseFirestore.getInstance()

        if(gym != null){
            val textView: TextView = findViewById(R.id.detailedActivityTv)
            val imageView: ImageView = findViewById(R.id.detailedActivityIv)
            textView.text = gym.name
            fetchExerciseByName(gym.name)
            Picasso.get().load(gym.image).into(imageView)
        }
    }

    private fun fetchExerciseByName(name: String) {
        firestore.collection("exercise")
            .whereEqualTo("title", name)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val title = document.getString("title") ?: ""
                    val imageUrl = document.getString("image") ?: ""
                    val subtitle = document.getString("subtitle") ?: ""
                    val owner = document.getString("owner") ?: ""
                    val duration = document.getString("duration") ?: ""
                    val exercises = document.get("exercises") as? ArrayList<HashMap<String, String>>

                    val ownerTv = findViewById<TextView>(R.id.ownerTv)
                    ownerTv.text = owner

                    val durationTv = findViewById<TextView>(R.id.durationTv)
                    durationTv.text = duration

                    val subtitleTv = findViewById<TextView>(R.id.subtitleTv)
                    subtitleTv.text = subtitle

                    exercises?.let {
                        for (exerciseMap in it) {
                            val name = exerciseMap["name"] ?: ""
                            val reps = exerciseMap["reps"] ?: ""
                            val sets = exerciseMap["sets"] ?: ""
                            exercisesList.add(ExerciseInWorkOut(name, reps, sets))
                        }
                    }
                    exerciseRecyclerView.adapter = ExerciseInWorkOutAdapter(exercisesList)

//                    val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)
//                    exercises?.let {
//                        var previousTextViewId = R.id.subtitleTv // Store the id of the previous TextView
//
//                        for (exercise in it) {
//                            val reps = exercise["reps"] ?: ""
//                            val sets = exercise["sets"] ?: ""
//                            val exerciseName = exercise["name"] ?: ""
//
//                            val exerciseDetails = "$exerciseName:  Reps: $reps, Sets: $sets"
//
//                            // Create a new TextView for each exercise and add it to the layout
//                            val newExerciseTextView = TextView(this)
//                            newExerciseTextView.text = exerciseDetails
//                            constraintLayout.addView(newExerciseTextView)
//
//                            // Set layout constraints for the new TextView
//                            val params = newExerciseTextView.layoutParams as ConstraintLayout.LayoutParams
//                            params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
//                            params.topToBottom = previousTextViewId // Set top constraint to the previous TextView
//                            params.topMargin = resources.getDimensionPixelSize(R.dimen.exercise_margin) // Set margin between exercises
//                            params.bottomMargin = resources.getDimensionPixelSize(R.dimen.exercise_margin) // Set bottom margin between exercises
//                            newExerciseTextView.layoutParams = params
//
//                            // Update previousTextViewId to current TextView's id
//                            previousTextViewId = newExerciseTextView.id
//                        }
//                    }

                    // You can process the fetched data here if needed

                    // Example to log data
                    Log.e("Details", "Title: $title, ImageUrl: $imageUrl, Subtitle: $subtitle, Owner: $owner, Duration: $duration, Exercises: $exercises")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Details", "Error: ${exception.message}", exception)
                Toast.makeText(
                    this@DetailsActivity,
                    "Error: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }



}

/*
Example to log:
            Log.e("Details", gym.name)
 */