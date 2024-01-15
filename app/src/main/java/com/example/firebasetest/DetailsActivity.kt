package com.example.firebasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deatiled)

        val gym = intent.getParcelableExtra<Gym>("gym")
        if(gym != null){
            val textView: TextView = findViewById(R.id.detailedActivityTv)
            val imageView: ImageView = findViewById(R.id.detailedActivityIv)

            textView.text = gym.name
            imageView.setImageResource(gym.image)
        }
    }
}