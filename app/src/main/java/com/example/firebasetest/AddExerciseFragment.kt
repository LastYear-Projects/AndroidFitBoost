package com.example.firebasetest

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasetest.databinding.ActivityHomeFragment2Binding
import com.example.firebasetest.databinding.FragmentAddexeceriseBinding
import com.example.firebasetest.databinding.FragmentFavoriteBinding
import com.example.firebasetest.databinding.FragmentHomeBinding
import com.example.firebasetest.databinding.FragmentModalNewExerciseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class AddExerciseFragment : Fragment() {
    private lateinit var binding: FragmentAddexeceriseBinding
    private lateinit var bindingTest: FragmentModalNewExerciseBinding
    private lateinit var imageView: ImageView
    private lateinit var customDialog: AlertDialog

    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var execeriseList: ArrayList<Execerise>
    private lateinit var execeriseAdapter: ExeceriseAdapter
    private lateinit var ExeceriseRecyclerView: RecyclerView
    private lateinit var titleTextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addexecerise, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()


        binding = FragmentAddexeceriseBinding.inflate(inflater, container, false)
        bindingTest = FragmentModalNewExerciseBinding.inflate(inflater, container, false)

        titleTextView = binding.textView15

        binding.execeriseRecyclerView.setHasFixedSize(true)
        binding.execeriseRecyclerView.layoutManager = LinearLayoutManager(activity)

        execeriseList = ArrayList()

        ExeceriseRecyclerView = binding.execeriseRecyclerView
        ExeceriseRecyclerView.setHasFixedSize(true)
        ExeceriseRecyclerView.layoutManager = LinearLayoutManager(activity)


        execeriseAdapter = ExeceriseAdapter(execeriseList){
            updateTitleWithExercisesCount()
        }
        binding.execeriseRecyclerView.adapter = execeriseAdapter

        binding.btnShort.setOnClickListener {
            resetButtonColors()
            it.setBackgroundColor(Color.GRAY)
            it.isActivated = true
        }
        binding.btnMedium.setOnClickListener {
            resetButtonColors()
            it.setBackgroundColor(Color.GRAY)
            it.isActivated = true
        }
        binding.btnLong.setOnClickListener {
            resetButtonColors()
            it.setBackgroundColor(Color.GRAY)
            it.isActivated = true
        }
        binding.btnAddExercises.setOnClickListener {
            showCustomDialog()
        }
        binding.btnCreateworkout.setOnClickListener {
            createWorkout()
        }

        return binding.root
    }

    private fun updateTitleWithExercisesCount() {
        val title = "Exercises (${execeriseList.size}) :"
        titleTextView.text = title
    }


    private fun resetButtonColors() {
        binding.btnShort.setBackgroundColor(Color.BLACK)
        binding.btnMedium.setBackgroundColor(Color.BLACK)
        binding.btnLong.setBackgroundColor(Color.BLACK)
        binding.btnShort.isActivated = false
        binding.btnMedium.isActivated = false
        binding.btnLong.isActivated = false

    }
    public fun createWorkout() {
        val workoutName = binding.workoutEt.text.toString()
        val workoutDuration = when {
            binding.btnShort.isActivated -> "Short"
            binding.btnMedium.isActivated -> "Medium"
            binding.btnLong.isActivated -> "Long"
            else -> "Medium"
        }
        val pictureUrl=binding.pictureUrlValue.text.toString()
        Log.e("test",workoutName)
        Log.e("test",workoutDuration)
        Log.e("test",pictureUrl)
        Log.e("test",execeriseList.size.toString())
        val owner=firebaseAuth.currentUser?.email.toString()

        val workout = hashMapOf(
            "title" to workoutName,
            "duration" to workoutDuration,
            "image" to pictureUrl,
            "exercises" to execeriseList,
            "owner" to owner
        )

        firestore.collection("exercise").add(workout)
            .addOnSuccessListener {
                Log.e("test", "Workout added successfully")
                Toast.makeText(requireContext(), "Workout added successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.e("test", "Error adding workout", it)
                Toast.makeText(requireContext(), "Error adding workout", Toast.LENGTH_SHORT).show()

            }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showCustomDialog() {
        // Inflate the custom layout
        val customLayout = layoutInflater.inflate(R.layout.fragment_modal_new_exercise, null)

        // Create the AlertDialog
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(customLayout)
        builder.setTitle("New Exercise")

        customDialog = builder.create()
        customDialog.show()

        val tvExerciseCreateExercise = customLayout.findViewById<TextView>(R.id.tvExerciseCreateExercise)
        tvExerciseCreateExercise.setOnClickListener {
            val exerciseName = customLayout.findViewById<EditText>(R.id.tvExerciseName).text.toString()
            val exerciseExplanation = customLayout.findViewById<EditText>(R.id.tvExerciseExplanation).text.toString()
            val exerciseRepeats = customLayout.findViewById<EditText>(R.id.tvExerciseExplanationRepeatsEditText).text.toString()
            val exerciseSets = customLayout.findViewById<EditText>(R.id.tvExerciseExplanationSetsEditText).text.toString()

            // Create a new exercise
            val newExercise = Execerise(exerciseName, exerciseRepeats, exerciseSets)

            // Add the new exercise to the list
            execeriseList.add(newExercise)
            updateTitleWithExercisesCount()

            // Notify the adapter about the data change
            execeriseAdapter.notifyDataSetChanged()

            // Close the dialog
            customDialog.dismiss()
        }

    }


}



