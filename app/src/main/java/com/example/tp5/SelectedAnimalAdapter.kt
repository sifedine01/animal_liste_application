package com.example.tp5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SelectedAnimalAdapter(private val selectedAnimals: List<Animal>) :
    RecyclerView.Adapter<SelectedAnimalAdapter.SelectedAnimalViewHolder>() {

    inner class SelectedAnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.selectedAnimalName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedAnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_selected_animal, parent, false)
        return SelectedAnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectedAnimalViewHolder, position: Int) {
        holder.nameText.text = selectedAnimals[position].name
    }

    override fun getItemCount(): Int = selectedAnimals.size
}