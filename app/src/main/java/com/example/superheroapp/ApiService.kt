package com.example.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ApiService { //interfaz que controla la comunicacion con la Api

    @GET("/api/8d39c82901c56c6d4fccc9d7cfd1d00b/search/{name}") //solicitud GET a la api con el nombre del heroe
    suspend fun getSuperheroes(@Path("name") superheroName:String): Response<SuperHeroDataResponse> //funcion que devuelve la respuesta de la Api con la informacion del item
    @GET("/api/8d39c82901c56c6d4fccc9d7cfd1d00b/{id}")//solicitud GET a la api con el id del heroe
    suspend fun getSuperheroDetail(@Path("id")superheroId:String):Response<SuperHeroDetailResponse>//funcion que devuelve la respuesta de la Api con la informacion del item
}


