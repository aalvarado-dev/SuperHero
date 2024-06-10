package com.example.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(//aqui tenemos los que nos devolvera la api con la solicitud del id que enviamos
    @SerializedName("name") val name: String, //contiene el nombre en la variable name del item seleccionado
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,//contiene el poder en la variable poder del item seleccionado
    @SerializedName("image") val image: SuperheroImageDetailResponse,//contiene la imagen en la variable imagen del item seleccionado
    @SerializedName("biography") val biography:Biography//contiene la biografia en la variable biography del item seleccionado

)

data class PowerStatsResponse( //clase que contiene la informacion de los poderes del heroe seleccionado
    @SerializedName("intelligence") val intelligence: String,//contiene la inteligencia en la variable intelligence del item seleccionado
    @SerializedName("strength") val strength: String,//contiene la fuerza en la variable strength del item seleccionado
    @SerializedName("speed") val speed: String,//contiene la velocidad en la variable velocidad del item seleccionado
    @SerializedName("durability") val durability: String,//contiene la durabilidad en la variable durability del item seleccionado
    @SerializedName("power") val power: String,//contiene el poder en la variable power del item seleccionado
    @SerializedName("combat") val combat: String//contiene los combates en la variable combat del item seleccionado
)

data class SuperheroImageDetailResponse(@SerializedName("url") val url:String) //classe que contiene la url de la imagen del item seleccionado

data class Biography( //funcion que contiene la informacion de la biografia del heroe o item seleccionado
    @SerializedName("full-name") val fullName:String,//contiene el nombre completo en la variable full name del item seleccionado
    @SerializedName("publisher") val publisher:String//contiene el publicador  en la variable publisher del item seleccionado
)