package com.example.firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.firebasetest.databinding.ActivitySignUpBinding
import com.example.firebasetest.user.RoomMainActivity
import com.example.firebasetest.user.model.RoomUser
import com.example.firebasetest.user.viewmodel.RoomUserViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mUserViewModel: RoomUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        mUserViewModel = ViewModelProvider(this).get(RoomUserViewModel::class.java)

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()
            val phone = binding.phoneEt.text.toString()
            val regex = Regex("^\\d+\$")

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty() && phone.isNotEmpty()) {
                binding.emailLayout.isErrorEnabled = false
                binding.passwordLayout.isErrorEnabled = false
                binding.confirmPasswordLayout.isErrorEnabled = false
                binding.phone.isErrorEnabled = false

                if (!regex.matches(phone)) {
                    Toast.makeText(this, "Phone must be digits only.", Toast.LENGTH_SHORT).show()
                    binding.phone.isErrorEnabled = true
                    binding.phone.error = "Phone must be digits only."
                } else {
                    binding.phone.isErrorEnabled = false
                    if (password == confirmPass) {
                        binding.confirmPasswordLayout.isErrorEnabled = false
                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    // Create RoomUser instance
                                    val user = RoomUser(0, binding.fullNameEt.text.toString(), email)
                                    // Add the user to the Room database
                                    mUserViewModel.addUser(user)

                                    Toast.makeText(
                                        this,
                                        "Account created Successfully!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent = Intent(this, SignInActivity::class.java)
//                                    val intent = Intent(this, RoomMainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this,
                                        it.exception?.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } else {
                        Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                        binding.confirmPasswordLayout.isErrorEnabled = true
                        binding.confirmPasswordLayout.error = "Password is not matching"
                    }
                }
            } else {
                Toast.makeText(this, "All the fields are required.", Toast.LENGTH_SHORT).show()
                email.isEmpty()&& password.isNotEmpty() && confirmPass.isNotEmpty() && phone.isNotEmpty()
                if(binding.emailEt.text.toString().isEmpty()){
                    binding.emailLayout.isErrorEnabled = true
                    binding.emailLayout.error = "Email can't be empty"
                }

                if(binding.passET.text.toString().isEmpty()){
                    binding.passwordLayout.isErrorEnabled = true
                    binding.passwordLayout.error = "Password can't be empty"
                }

                if(binding.confirmPassEt.text.toString().isEmpty()){
                    binding.confirmPasswordLayout.isErrorEnabled = true
                    binding.confirmPasswordLayout.error = "Confirm Password can't be empty"
                }

                if(binding.phoneEt.text.toString().isEmpty()) {
                    binding.phone.isErrorEnabled = true
                    binding.phone.error = "Phone can't be empty"
                }

            }
        }
    }
}