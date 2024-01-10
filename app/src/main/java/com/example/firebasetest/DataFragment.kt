package com.example.firebasetest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class DataFragment : Fragment() {

    val args: DataFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_data, container, false)

        val message = args.message.toString()
        view.findViewById<TextView>(R.id.customText).text = message


        val button = view.findViewById<Button>(R.id.button4)
        button.setOnClickListener{
            findNavController().navigate(R.id.action_dataFragment_to_homeFragment)
        }
        return view
    }
}