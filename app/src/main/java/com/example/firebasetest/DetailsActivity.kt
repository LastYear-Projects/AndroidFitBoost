package com.example.firebasetest

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var exerciseRecyclerView: RecyclerView
    private lateinit var exercisesList: ArrayList<ExerciseInWorkOut>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        firebaseAuth = FirebaseAuth.getInstance()
        val gym = intent.getParcelableExtra<Workout>("gym")
        exerciseRecyclerView = findViewById(R.id.exerciseRecyclerView)
//        exerciseAdapter = ExerciseInWorkOutAdapter(emptyList()) // Initialize with an empty list
        exercisesList = ArrayList()
//        exerciseRecyclerView.adapter = exerciseAdapter
        exerciseRecyclerView.layoutManager = LinearLayoutManager(this)

        fireStore = FirebaseFirestore.getInstance()

        if(gym != null){
            val textView: TextView = findViewById(R.id.detailedActivityTv)
            val imageView: ImageView = findViewById(R.id.detailedActivityIv)
            textView.text = gym.name
            fetchExerciseByName(gym.name)
            Picasso.get().load(gym.image).into(imageView)
        }
    }

    private fun fetchExerciseByName(name: String) {
        fireStore.collection("exercise")
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

                    val user = firebaseAuth.currentUser?.email
                    if(user==owner){
                        findViewById<Button>(R.id.removeWorkout).visibility = View.VISIBLE
                    }

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

// Example to log data
                    Log.e("Details", "Title: $title, ImageUrl: $imageUrl, Subtitle: $subtitle, Owner: $owner, Duration: $duration, Exercises: $exercises")
                }
                findViewById<Button>(R.id.removeWorkout).setOnClickListener {
                    val name = intent.getParcelableExtra<Workout>("gym")?.name

                    fireStore.collection("location").whereEqualTo("title", name)
                        .get()
                        .addOnSuccessListener{result ->
                            for(document in result){
                                fireStore.collection("location")
                                    .document(document.id)
                                    .delete()
                            }

                        }

                    fireStore.collection("exercise")
                        .whereEqualTo("title", name)
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result) {
                                // Delete each document that matches the condition
                                fireStore.collection("exercise")
                                    .document(document.id)
                                    .delete()
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Document was successfully deleted!", Toast.LENGTH_SHORT).show()
                                        finishAndGoToHomeFragment()
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w("TAG", "Error deleting document", e)
                                    }
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w("TAG", "Error getting documents: ", exception)
                        }
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

    private fun finishAndGoToHomeFragment() {
        val user = firebaseAuth.currentUser
        if (user != null) {
            val intent = Intent(this, HomeFragment2Activity::class.java)
            startActivity(intent)
            finish()
        }
    }

}