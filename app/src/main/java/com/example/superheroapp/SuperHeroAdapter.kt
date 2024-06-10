package com.example.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter( //Adapter del super hero que se mostrara con un listado
    var superheroList: List<SuperheroItemResponse> = emptyList(), //instancia de la lista inicializada en vacio
    private val onItemSelected: (String) -> Unit //variable del item seleccionado
    ) :
    RecyclerView.Adapter<SuperheroViewHolder>() { //utiliza superhiroviewholder como el tipo de viewholder

    fun updateList(list: List<SuperheroItemResponse>) { //funcion que actualiza los datos de la lista recibida del heroe buscado
        superheroList = list //indico los valores a la lista del adaptador recibidos por parametro
        notifyDataSetChanged() //indicamos al adapter que se actualize con los valores recibidos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder { //funcion que crea el elemento de la vista
        return SuperheroViewHolder( //devolvemos el holder
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false) //muestra el item en el layout
        )
    }

    override fun onBindViewHolder(viewholder: SuperheroViewHolder, position: Int) {
        viewholder.bind(superheroList[position], onItemSelected) //indicamos la posicion del item selecionado y le pasamos el item seleccionado
    }

    override fun getItemCount() = superheroList.size //devuleve el tamaño de la lista del item
}