//package com.example.firebasetest.Adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import androidx.recyclerview.widget.RecyclerView
//import com.example.firebasetest.R
//import android.view.ViewGroup
//import android.widget.TextView
//
//
//class ExerciseRecyclerAdapter(private val exercises: ArrayList<Exercise>): RecyclerView.Adapter<ExerciseRecyclerAdapter.ExerciseViewHolder>() {
//
//
//    class ExerciseViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val exerciseName: TextView = itemView.findViewById(R.id.tvExerciseName)
//        val exerciseDescription: TextView = itemView.findViewById(R.id.tvExerciseDescription)
//        val exerciseSets: TextView = itemView.findViewById(R.id.tvExerciseSets)
//        val exerciseRepeats: TextView = itemView.findViewById(R.id.tvExerciseRepeats)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
//        val view= LayoutInflater.from(parent.context).inflate(R.layout.exercise_layout_row, parent, false)
//        return ExerciseViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return exercises.size
//    }
//
//    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
//        val currentItem = exercises[position]
//        holder.exerciseName.text = currentItem.name
//        holder.exerciseSets.text = currentItem.sets
//        holder.exerciseRepeats.text = currentItem.repeats
//    }
//
//}