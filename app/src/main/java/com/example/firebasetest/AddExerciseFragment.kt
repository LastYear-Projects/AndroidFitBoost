package com.example.firebasetest

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.firebasetest.databinding.ActivityHomeFragment2Binding
import com.example.firebasetest.databinding.FragmentAddexeceriseBinding
import com.example.firebasetest.databinding.FragmentFavoriteBinding
import com.example.firebasetest.databinding.FragmentHomeBinding
import com.example.firebasetest.databinding.FragmentModalNewExerciseBinding


class AddExerciseFragment : Fragment() {
    private lateinit var binding: FragmentAddexeceriseBinding
    private lateinit var bindingTest: FragmentModalNewExerciseBinding
    private lateinit var imageView: ImageView
    private lateinit var customDialog: AlertDialog

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addexecerise, container, false)

        binding = FragmentAddexeceriseBinding.inflate(inflater, container, false)
        bindingTest = FragmentModalNewExerciseBinding.inflate(inflater, container, false)
        imageView = binding.imgSave

        imageView.setOnClickListener {
            pickImageGallery()
        }

        binding.btnBeginner.setOnClickListener {
            resetButtonColors()
            it.setBackgroundColor(Color.GRAY)
        }
        binding.btnIntermediate.setOnClickListener {
            resetButtonColors()
            it.setBackgroundColor(Color.GRAY)
        }
        binding.btnAdvanced.setOnClickListener {
            resetButtonColors()
            it.setBackgroundColor(Color.GRAY)
        }


        binding.btnAddExercises.setOnClickListener {
            showCustomDialog()
        }

        return binding.root
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, FavoriteFragment.IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("result code: $resultCode" )
        if(resultCode == -1){
            imageView.setImageURI(data?.data)
        }
    }

    private fun resetButtonColors() {
        binding.btnBeginner.setBackgroundColor(Color.BLACK)
        binding.btnIntermediate.setBackgroundColor(Color.BLACK)
        binding.btnAdvanced.setBackgroundColor(Color.BLACK)
    }

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
            //TODO -> save the exercise before we close.
            customDialog.dismiss()
        }
    }


}