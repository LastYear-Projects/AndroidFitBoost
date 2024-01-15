package com.example.firebasetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GymAdapter(private val gymList: ArrayList<Gym>)
    : RecyclerView.Adapter<GymAdapter.GymViewHolder>() {

        var onItemClick: ((Gym) -> Unit)? = null

    class GymViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.homePageImageView)
        val textView: TextView = itemView.findViewById(R.id.homePageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GymViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return GymViewHolder(view)
    }

    override fun onBindViewHolder(holder: GymViewHolder, position: Int) {
        val gym = gymList[position]
        holder.imageView.setImageResource(gym.image)
        holder.textView.text = gym.name

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(gym)
        }
    }

    override fun getItemCount(): Int {
        return gymList.size
    }


}