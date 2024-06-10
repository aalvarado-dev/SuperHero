package com.example.superheroapp

import com.google.gson.annotations.SerializedName


data class SuperHeroDataResponse(//aqui tenemos los que nos devolvera la api con la solicitud que enviamos
    @SerializedName("response") val response: String, //devuelve el response
    @SerializedName("results") val superheroes: List<SuperheroItemResponse>//devuelve la lista
)

data class SuperheroItemResponse( //data clas que contiene la informacion de los items
    @SerializedName("id") val superheroId: String, //contiene el id del iten
    @SerializedName("name") val name: String, //contiene el nombre del item
    @SerializedName("image") val superheroImage:SuperheroImageResponse //contiene la imagen del item
)

data class SuperheroImageResponse(@SerializedName("url") val url:String) //data class que contiene el url de la imagen del item selecionado