package com.example.firebasetest

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebasetest.databinding.ActivityHomeFragment2Binding
import com.example.firebasetest.databinding.FragmentAddexeceriseBinding
import com.example.firebasetest.databinding.FragmentHomeBinding


class AddExerciseFragment : Fragment() {
    private lateinit var binding: FragmentAddexeceriseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addexecerise, container, false)

        binding = FragmentAddexeceriseBinding.inflate(inflater, container, false)

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

        return binding.root
    }

    private fun resetButtonColors() {
        binding.btnBeginner.setBackgroundColor(Color.BLACK)
        binding.btnIntermediate.setBackgroundColor(Color.BLACK)
        binding.btnAdvanced.setBackgroundColor(Color.BLACK)
    }



}