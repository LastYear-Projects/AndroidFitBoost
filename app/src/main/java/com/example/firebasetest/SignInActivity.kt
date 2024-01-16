package com.example.firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasetest.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){

//                        val intent = Intent(this, MainActivity::class.java)
                        val user = firebaseAuth.currentUser
                        if (user != null) {
                            val intent = Intent(this, HomeFragment2Activity::class.java)
                            intent.putExtra("USER", user)
                            startActivity(intent)
                            finish()
                        }
//                        val intent = Intent(this, HomeFragment2Activity::class.java)
//                        startActivity(intent)
//                        finish()
                    }else{
                        Toast.makeText(this, "Email or password are incorrect.", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "All the fields are required.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.tvForgotPassword.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)

            finish()
        }
    }

    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

/*
it.putExtra("EXTRA_MESSAGE", create a variable with the string)
    }

val message = intent.getStringExtra("EXTRA_MESSAGE") -> in the second activity.
 */