package com.example.firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasetest.databinding.ActivityHomePageBinding
import com.google.firebase.auth.FirebaseAuth

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private  lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val user_email = intent.getStringExtra("details")


        binding.tvEmail.apply {
            text = user_email.toString()
        }

        initButtons()
    }

    private fun initButtons(){
        binding.button2.setOnClickListener{
            if(firebaseAuth.currentUser != null){
                firebaseAuth.signOut()
                Toast.makeText(this, "Logout.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)

                finish()
            }
        }

        binding.btnUpdatePassword.setOnClickListener{
            val user = firebaseAuth.currentUser
            val password = binding.passET.text.toString()
            user?.updatePassword(password)?.addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this, "Password changed!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Something wrong, please try again later...", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnUpdateEmail.setOnClickListener{
            val user = firebaseAuth.currentUser
            val email = binding.emailEt.text.toString()
            user?.updateEmail(email)?.addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this, "Email changed!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnDeleteAccount.setOnClickListener{
            val user = firebaseAuth.currentUser
            user?.delete()?.addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "User Removed.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)

                    finish()
                }else{
                    Toast.makeText(this, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}