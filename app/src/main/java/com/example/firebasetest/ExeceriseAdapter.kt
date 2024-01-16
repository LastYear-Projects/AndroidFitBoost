package com.example.firebasetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class ExeceriseAdapter(
    private val execeriseList: ArrayList<Execerise>,
    private val onItemRemoved: () -> Unit)
    : RecyclerView.Adapter<ExeceriseAdapter.ExeceriseViewHolder>() {

        class ExeceriseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val name: TextView = itemView.findViewById(R.id.execeriseNameTv)
            val reps: TextView = itemView.findViewById(R.id.execeriseRepsTv)
            val sets: TextView = itemView.findViewById(R.id.execeriseSetsTv)
            val removeButton: Button = itemView.findViewById(R.id.btnRemoveExercise)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExeceriseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item_execerise, parent, false)
        return ExeceriseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return execeriseList.size
    }

    override fun onBindViewHolder(holder: ExeceriseViewHolder, position: Int) {
        val execerise = execeriseList[position]
        holder.name.text = execerise.name
        holder.reps.text = execerise.reps
        holder.sets.text = execerise.sets

        holder.removeButton.setOnClickListener {
            // Remove the item from the list
            removeItem(position)
            holder.let { it1 -> Snackbar.make(it, "Item was removed", Snackbar.LENGTH_LONG).show() }
        }

    }
    private fun removeItem(position: Int) {
        execeriseList.removeAt(position)
        notifyItemRemoved(position)
        onItemRemoved()
    }
}