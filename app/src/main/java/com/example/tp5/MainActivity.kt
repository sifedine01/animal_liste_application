package com.example.tp5

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var selectedAnimals: MutableList<Animal>
    private lateinit var mainAdapter: AdapterAnimaux
    private lateinit var selectedAdapter: SelectedAnimalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        selectedAnimals = mutableListOf()

        val lista = mutableListOf(
            Animal("Chat", "Mammal", R.drawable.cat),
            Animal("Chien", "Mammal", R.drawable.dog),
            Animal("Perroquet", "Oiseau", R.drawable.parrot),
            Animal("Serpent", "Reptile", R.drawable.snake),
            Animal("Chien", "Mammal", R.drawable.dog),
            Animal("Perroquet", "Oiseau", R.drawable.parrot),
            Animal("Serpent", "Reptile", R.drawable.snake)
        )

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val checkedRecyclerView = findViewById<RecyclerView>(R.id.checked)

        recyclerView.layoutManager = LinearLayoutManager(this)
        checkedRecyclerView.layoutManager = LinearLayoutManager(this)

        // Main adapter with checkbox callback
        mainAdapter = AdapterAnimaux(lista) { animal, isChecked ->
            animal.isSelected = isChecked
            if (isChecked) {
                if (!selectedAnimals.contains(animal)) {
                    selectedAnimals.add(animal)
                    Toast.makeText(this, "You chose: ${animal.name}", Toast.LENGTH_SHORT).show()
                }
            } else {
                selectedAnimals.remove(animal)
                Toast.makeText(this, "You removed: ${animal.name}", Toast.LENGTH_SHORT).show()
            }
            selectedAdapter.notifyDataSetChanged()
        }

        selectedAdapter = SelectedAnimalAdapter(selectedAnimals)

        recyclerView.adapter = mainAdapter
        checkedRecyclerView.adapter = selectedAdapter

        radioGroup.setOnCheckedChangeListener { _, id ->
            if (id == R.id.affichageGrille) {
                recyclerView.layoutManager = GridLayoutManager(this, 2)
            } else {
                recyclerView.layoutManager = LinearLayoutManager(this)
            }
        }
    }
}
