package com.example.firebasetest.user.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasetest.R
import com.example.firebasetest.user.model.RoomUserListAdapter
import com.example.firebasetest.user.viewmodel.RoomUserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RoomUserListFragment : Fragment() {

    private lateinit var mRoomUserViewModel: RoomUserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_room_user_list, container, false)

        val adapter = RoomUserListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.RoomRecyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mRoomUserViewModel = ViewModelProvider(this).get(RoomUserViewModel::class.java)
        mRoomUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {user->
            adapter.setData(user)
        })

        view.findViewById<FloatingActionButton>(R.id.RoomFloatingActionButton).setOnClickListener{
            findNavController().navigate(R.id.action_roomUserListFragment_to_roomUserAddFragment)
        }
        return view
    }

}