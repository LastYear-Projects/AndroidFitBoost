package com.example.firebasetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class WorkoutAdapter(private val gymList: ArrayList<Workout>)
    : RecyclerView.Adapter<WorkoutAdapter.GymViewHolder>() {

        var onItemClick: ((Workout) -> Unit)? = null

    class GymViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.homePageImageView)
        val textView: TextView = itemView.findViewById(R.id.homePageTextView)
        val subtitleView: TextView = itemView.findViewById(R.id.homePageSubTitleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GymViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return GymViewHolder(view)
    }

    override fun onBindViewHolder(holder: GymViewHolder, position: Int) {
        val gym = gymList[position]
        // Load image using Picasso library
        Picasso.get()
            .load(gym.image)
            .into(holder.imageView)
        holder.textView.text = gym.name
        holder.subtitleView.text = gym.subtitle

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(gym)
        }
    }

    override fun getItemCount(): Int {
        return gymList.size
    }


}