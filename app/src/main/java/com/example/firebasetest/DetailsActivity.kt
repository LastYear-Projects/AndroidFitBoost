package com.example.firebasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val gym = intent.getParcelableExtra<Workout>("gym")

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
                    val exercises = document.get("exercises")

                    val ownerTv = findViewById<TextView>(R.id.ownerTv)
                    ownerTv.text = owner

                    val durationTv = findViewById<TextView>(R.id.durationTv)
                    durationTv.text = duration

                    val subtitleTv = findViewById<TextView>(R.id.subtitleTv)
                    subtitleTv.text = subtitle

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