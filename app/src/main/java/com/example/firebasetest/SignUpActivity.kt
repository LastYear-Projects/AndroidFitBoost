package com.example.firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.firebasetest.databinding.ActivitySignUpBinding
import com.example.firebasetest.user.RoomMainActivity
import com.example.firebasetest.user.model.RoomUser
import com.example.firebasetest.user.viewmodel.RoomUserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mUserViewModel: RoomUserViewModel
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        mUserViewModel = ViewModelProvider(this).get(RoomUserViewModel::class.java)

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.btnSingUp.setOnClickListener {
            val fullName = binding.fullNameEt.text.toString()
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()
            val phone = binding.phoneEt.text.toString()
            val weight = binding.weightEt.text.toString()
            val height = binding.weightEt.text.toString()
            val gender = binding.weightEt.text.toString()
            val age = binding.weightEt.text.toString()
            val regex = Regex("^\\d+\$")

            if (fullName.isNotEmpty() &&
                phone.isNotEmpty() &&
                email.isNotEmpty() &&
                password.isNotEmpty() &&
                confirmPass.isNotEmpty() &&
                weight.isNotEmpty() &&
                height.isNotEmpty() &&
                gender.isNotEmpty() &&
                age.isNotEmpty()) {
                binding.emailLayout.isErrorEnabled = false
                binding.passwordLayout.isErrorEnabled = false
                binding.confirmPasswordLayout.isErrorEnabled = false
                binding.phone.isErrorEnabled = false
                binding.fullName.isErrorEnabled = false
                binding.weightLayout.isErrorEnabled = false
                binding.heightLayout.isErrorEnabled = false
                binding.genderLayout.isErrorEnabled = false
                binding.ageLayout.isErrorEnabled = false

                if (!regex.matches(phone)) {
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
                                    val user = RoomUser(0, fullName , email, weight, height, gender, age, phone)
                                    // Add the user to the Room database
                                    mUserViewModel.addUser(user)

                                    // Save the user to Firestore
                                    saveUserToFirestore(user)

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
                        binding.confirmPasswordLayout.isErrorEnabled = true
                        binding.confirmPasswordLayout.error = "Password is not matching"
                    }
                }
            } else {
                if(binding.fullNameEt.text.toString().isEmpty()){
                    binding.fullName.isErrorEnabled = true
                    binding.fullName.error = "FullName can't be empty"
                }

                if(binding.phoneEt.text.toString().isEmpty()) {
                    binding.phone.isErrorEnabled = true
                    binding.phone.error = "Phone can't be empty"
                }

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

                if(binding.weightEt.text.toString().isEmpty()){
                    binding.weightLayout.isErrorEnabled = true
                    binding.weightLayout.error = "Weight can't be empty"
                }

                if(binding.heightEt.text.toString().isEmpty()){
                    binding.heightLayout.isErrorEnabled = true
                    binding.heightLayout.error = "Height can't be empty"
                }

                if(binding.genderEt.text.toString().isEmpty()){
                    binding.genderLayout.isErrorEnabled = true
                    binding.genderLayout.error = "Gender can't be empty"
                }

                if(binding.ageEt.text.toString().isEmpty()){
                    binding.ageLayout.isErrorEnabled = true
                    binding.ageLayout.error = "Age can't be empty"
                }

            }
        }

    }

    private fun saveUserToFirestore(user: RoomUser) {
        val userMap = hashMapOf(
            "fullName" to user.fullName,
            "email" to user.email,
            "weight" to user.weight,
            "height" to user.height,
            "gender" to user.gender,
            "age" to user.age,
            "phone" to user.phone
        )

        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val userDocument = firestore.collection("users").document(userId)

            userDocument.set(userMap)
                .addOnSuccessListener { documentReference ->
                    // Get the document ID from the DocumentReference using getId()
                    val documentId = documentReference
                    Log.d("Firestore", "User data added successfully. Document ID: $documentId")
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error adding user data", e)
                }
        } else {
            Log.e("Firestore", "Current user is null. User data not saved to Firestore.")
        }
    }



}