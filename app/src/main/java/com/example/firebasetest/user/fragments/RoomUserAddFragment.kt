package com.example.firebasetest.user.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.firebasetest.R
import com.example.firebasetest.user.model.RoomUser
import com.example.firebasetest.user.viewmodel.RoomUserViewModel


class RoomUserAddFragment : Fragment() {

    private lateinit var mUserViewModel: RoomUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_room_user_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(RoomUserViewModel::class.java)

        view.findViewById<Button>(R.id.RoomUserAddBtn).setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
//        val fullName = view?.findViewById<EditText>(R.id.RoomFullNameEt)?.text.toString()
//        val email = view?.findViewById<EditText>(R.id.RoomEmailEt)?.text.toString()
//
//        if(inputCheck(fullName, email)){
//            val user = RoomUser(0, fullName, email)
//            // Add the user to the database
//            mUserViewModel.addUser(user)
//            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
//            // navigate back to the userList page.
//            findNavController().navigate(R.id.action_roomUserAddFragment_to_roomUserListFragment)
//        }else{
//            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun inputCheck(fullName: String, email: String): Boolean{
        return (fullName.isNotEmpty() && email.isNotEmpty())
    }
}