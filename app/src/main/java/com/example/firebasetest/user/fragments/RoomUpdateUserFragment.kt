package com.example.firebasetest.user.fragments

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.example.firebasetest.R
import com.example.firebasetest.user.model.RoomUser
import com.example.firebasetest.user.viewmodel.RoomUserViewModel


class RoomUpdateUserFragment : Fragment() {

    private val args by navArgs<RoomUpdateUserFragmentArgs>()

    private lateinit var mUserViewModel: RoomUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_room_update_user, container, false)

        mUserViewModel = ViewModelProvider(this).get(RoomUserViewModel::class.java)

        view.findViewById<EditText>(R.id.RoomUpdateFullNameEt).setText(args.currentUser.fullName)
        view.findViewById<EditText>(R.id.RoomUpdateEmailEt).setText(args.currentUser.email)

        view.findViewById<Button>(R.id.RoomUpdateUserAddBtn).setOnClickListener{
            updateItem()
        }

        view.findViewById<Button>(R.id.room_menu_deleteBTN).setOnClickListener {
            deleteUser()
        }

        // Add Menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
//        val fullName = view?.findViewById<EditText>(R.id.RoomUpdateFullNameEt)
//        val email = view?.findViewById<EditText>(R.id.RoomUpdateEmailEt)
//
//        if(inputCheck(fullName.toString(), email.toString())){
//            val updatedUser = RoomUser(args.currentUser.id, fullName?.text.toString(), email?.text.toString())
//            mUserViewModel.updateUser(updatedUser)
//            Toast.makeText(requireContext(),"Updated Successfully!", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_roomUpdateUserFragment_to_roomUserListFragment)
//        }else{
//            Toast.makeText(requireContext(),"Please fill all fields.", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun inputCheck(fullName: String, email: String): Boolean{
        return (fullName.isNotEmpty() && email.isNotEmpty())
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Successfully removed: ${args.currentUser.fullName}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_roomUpdateUserFragment_to_roomUserListFragment)

        }

        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Delete ${args.currentUser.fullName}?")
        builder.create().show()

    }


}