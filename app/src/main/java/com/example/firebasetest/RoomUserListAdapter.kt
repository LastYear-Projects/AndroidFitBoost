package com.example.firebasetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RoomUserListAdapter: RecyclerView.Adapter<RoomUserListAdapter.MyViewHolder>() {

    private var userList = emptyList<RoomUser>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.room_custom_user_row,parent,false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.RoomList_FullName).text = currentItem.fullName
        holder.itemView.findViewById<TextView>(R.id.RoomList_Email).text = currentItem.email
        holder.itemView.findViewById<TextView>(R.id.RoomList_Id).text = currentItem.id.toString()

        // after we click on a user we will navigate to the updateFragment with the currentItem details.
        holder.itemView.findViewById<ConstraintLayout>(R.id.RoomRowLayout).setOnClickListener {
            val action = RoomUserListFragmentDirections.actionRoomUserListFragmentToRoomUpdateUserFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<RoomUser>){
        this.userList = user
        notifyDataSetChanged()
    }
}