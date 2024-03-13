package com.example.firebasetest

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasetest.databinding.FragmentAddWorkoutBinding
import com.example.firebasetest.databinding.FragmentModalNewExerciseBinding


class AddWorkoutFragment : Fragment() {
    private lateinit var binding: FragmentAddWorkoutBinding
    private lateinit var bindingTest: FragmentModalNewExerciseBinding
    private lateinit var imageUrl: String
    private lateinit var customDialog: AlertDialog

    private lateinit var execeriseList: ArrayList<Execerise>
    private lateinit var execeriseAdapter: ExeceriseAdapter
    private lateinit var ExeceriseRecyclerView: RecyclerView
    private lateinit var titleTextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_workout, container, false)

        binding = FragmentAddWorkoutBinding.inflate(inflater, container, false)
        bindingTest = FragmentModalNewExerciseBinding.inflate(inflater, container, false)
        imageUrl = binding.tiPictureUrl.toString()
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



        binding.btnShortWorkout.setOnClickListener {
            resetButtonColors()
            it.setBackgroundColor(Color.GRAY)
        }
        binding.btnMediumWorkout.setOnClickListener {
            resetButtonColors()
            it.setBackgroundColor(Color.GRAY)
        }
        binding.btnLongWorkout.setOnClickListener {
            resetButtonColors()
            it.setBackgroundColor(Color.GRAY)
        }
        binding.btnAddExercises.setOnClickListener {
            showCustomDialog()
        }

        return binding.root
    }

    private fun updateTitleWithExercisesCount() {
        val title = "Exercises (${execeriseList.size}) :"
        titleTextView.text = title
    }

//    private fun pickImageGallery() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, FavoriteFragment.IMAGE_REQUEST_CODE)
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        println("result code: $resultCode" )
//        if(resultCode == -1){
//            imageUrl.text = data?.data.toString()
//        }
//    }

    private fun resetButtonColors() {
        binding.btnShortWorkout.setBackgroundColor(Color.BLACK)
        binding.btnMediumWorkout.setBackgroundColor(Color.BLACK)
        binding.btnLongWorkout.setBackgroundColor(Color.BLACK)
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



