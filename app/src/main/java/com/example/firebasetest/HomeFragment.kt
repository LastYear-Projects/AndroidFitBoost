package com.example.firebasetest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import org.w3c.dom.Text


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)



        val button = view.findViewById<Button>(R.id.button3)
        button.setOnClickListener{
            val userInput = view.findViewById<EditText>(R.id.inputText).text.toString()
            val action = HomeFragmentDirections.actionHomeFragmentToDataFragment(userInput)
            findNavController().navigate(action)
        }
        return view
    }

}