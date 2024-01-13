package com.example.firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasetest.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()
            val phone = binding.phoneEt.text.toString()
            val regex = Regex("^\\d+\$")

            if(email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty() && phone.isNotEmpty()){
                if(!regex.matches(phone)){
                    Toast.makeText(this, "Phone must be digits only.", Toast.LENGTH_SHORT).show()
                }else{
                    if(password == confirmPass){
                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText(this, "Account created Successfully!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, SignInActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                Toast.makeText(this, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "All the fields are required.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}