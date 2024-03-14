package com.example.firebasetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseInWorkOutAdapter (private val exercises: List<ExerciseInWorkOut>) :
    RecyclerView.Adapter<ExerciseInWorkOutAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.exerciseName)
        val repsTextView: TextView = itemView.findViewById(R.id.reps)
        val setsTextView: TextView = itemView.findViewById(R.id.sets)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.nameTextView.text = exercise.name
        holder.repsTextView.text = exercise.reps
        holder.setsTextView.text = exercise.sets
    }

    override fun getItemCount(): Int {
        return exercises.size
    }
}