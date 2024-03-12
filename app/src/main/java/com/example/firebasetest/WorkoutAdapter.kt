package com.example.firebasetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class WorkoutAdapter(private val workoutList: ArrayList<Workout>)
    : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

        var onItemClick: ((Workout) -> Unit)? = null

    class WorkoutViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.homePageImageView)
        val textView: TextView = itemView.findViewById(R.id.homePageTextView)
        val subtitleView: TextView = itemView.findViewById(R.id.homePageSubTitleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val gym = workoutList[position]
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
        return workoutList.size
    }


}