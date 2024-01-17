package com.example.firebasetest

import ProfileFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.firebasetest.databinding.ActivityHomeFragment2Binding
import com.google.firebase.auth.FirebaseAuth

class HomeFragment2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeFragment2Binding

    private  lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeFragment2Binding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()

        setContentView(binding.root)
        replaceFragment(HomeFragment())




        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.exercise -> replaceFragment(AddExerciseFragment())
//                R.id.favorite -> replaceFragment(FavoriteFragment())
                R.id.map -> {
                    val intent = Intent(this, CurrentAndroidLocation::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.logout -> {
                    if(firebaseAuth.currentUser != null){
                        val builder = AlertDialog.Builder(this)
                        builder.setPositiveButton("Yes"){_,_->
                            firebaseAuth.signOut()
                            Toast.makeText(this, "Logout...", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        builder.setNegativeButton("No"){_,_->

                        }
                        builder.setTitle("Logout?")
                        builder.create().show()
                    }
                }
                else ->{

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}