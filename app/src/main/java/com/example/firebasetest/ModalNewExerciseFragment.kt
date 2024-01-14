package com.example.firebasetest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebasetest.databinding.FragmentFavoriteBinding
import com.example.firebasetest.databinding.FragmentModalNewExerciseBinding


class ModalNewExerciseFragment : Fragment() {
    private lateinit var binding: FragmentModalNewExerciseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_modal_new_exercise, container, false)
        binding = FragmentModalNewExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

}