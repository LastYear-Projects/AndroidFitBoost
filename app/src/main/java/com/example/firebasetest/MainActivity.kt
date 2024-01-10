
package com.example.firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.firebasetest.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val user = firebaseAuth.currentUser
            if( user != null){
                // Make this page like a Loader that before the pages are upload they will see a Loader.
                val intent = Intent(this, HomePageActivity::class.java).also {
                    it.putExtra("details", user.email)
                    startActivity(it)
                    finish()
                } //Create the HomePage and change it for the homepage.
            }else{
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }
        },3000)

    }
}
