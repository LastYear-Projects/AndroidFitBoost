package com.example.firebasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val gym = intent.getParcelableExtra<Gym>("gym")
        if(gym != null){
            val textView: TextView = findViewById(R.id.detailedActivityTv)
            val imageView: ImageView = findViewById(R.id.detailedActivityIv)
            textView.text = gym.name
            Picasso.get().load(gym.image).into(imageView)
        }
    }
}

/*
Example to log:
            Log.e("Details", gym.name)
 */