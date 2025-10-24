package com.example.tp5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdapterAnimaux(
    private val animals: MutableList<Animal>, // make mutable for deletion
    private val onCheckedChangeListener: (Animal, Boolean) -> Unit
) : RecyclerView.Adapter<AdapterAnimaux.AnimalViewHolder>() {

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val animalName: TextView = itemView.findViewById(R.id.NomAnimal)
        private val animalSpecies: TextView = itemView.findViewById(R.id.especeAnimal)
        private val animalImage: ImageView = itemView.findViewById(R.id.imageAnimal)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
        private val buttonDetails: ImageButton = itemView.findViewById(R.id.buttonDetails)
        private val buttonSupprimer: ImageButton = itemView.findViewById(R.id.buttonSupprimer)

        fun bind(animal: Animal) {
            animalName.text = animal.name
            animalSpecies.text = animal.species
            animalImage.setImageResource(animal.imageResId)

            // Prevent recycled listeners from firing
            checkBox.setOnCheckedChangeListener(null)
            checkBox.isChecked = animal.isSelected

            // ✅ Handle checkbox selection
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                onCheckedChangeListener(animal, isChecked)
            }

            // ✅ Handle info/details button click
            buttonDetails.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    "Info: ${animal.name} (${animal.species})",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // ✅ Handle delete button click
            buttonSupprimer.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val removed = animals.removeAt(position)
                    notifyItemRemoved(position)
                    Toast.makeText(
                        itemView.context,
                        "${removed.name} supprimé",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.animal_item, parent, false) // use your CardView layout
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(animals[position])
    }

    override fun getItemCount(): Int = animals.size
}
