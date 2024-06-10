package com.example.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) { //funcion que recibe la view del parametro

    private val binding = ItemSuperheroBinding.bind(view) //variable que almacena el binding

    fun bind(superheroItemResponse: SuperheroItemResponse, onItemSelected: (String) -> Unit) { //funcion
        binding.tvSuperheroName.text = superheroItemResponse.name //guardo el nombre enviado por el response en el layout
        Picasso.get().load(superheroItemResponse.superheroImage.url).into(binding.ivSuperhero) //utiliza la libreria picasso para mostrar la imagen con el url
        binding.root.setOnClickListener { onItemSelected(superheroItemResponse.superheroId) } //controla cada vez que se presiona el item se envia el id
    }
}