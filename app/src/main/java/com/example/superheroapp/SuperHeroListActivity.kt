package com.example.superheroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.DetailSuperheroActivity.Companion.EXTRA_ID
import com.example.superheroapp.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit //declaro el objeto retrofit
    private lateinit var adapter: SuperHeroAdapter //variable que almacena el adapter del tipo superhero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit() //se llama cuando se crea la pantalla
        initUI()
    }

    private fun initUI(){ //funcion que controla el listener de buscar y pulsa
        binding.serachView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean { //funcion que controla el evento de cuando se pulsa el boton de buscar
                searchByName(query.orEmpty()) //funcion que controla el evento de buscr pasando el texto indicado si no hay nada escrito se devuelve una cadena vacia
                return false //siempre se retorna false
            }
            override fun onQueryTextChange(newText: String?): Boolean { //funcion que controla el evento de cuando se escribe en el buscador
                return false //no hacemos nada
            }
        })

        adapter = SuperHeroAdapter { superheroId ->  navigateToDetail(superheroId) } //creo el adapter de tipo superhero que recibe el id del item seleccionado
        binding.rvSuperhero.setHasFixedSize(true) //prepara el recycler view
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperhero.adapter = adapter //indica el adapter es el que acaba de inicializar
    }

    private fun searchByName(query: String) { //funcion que controla la busqueda del super hero escrito
        binding.progressBar.isVisible = true //una vez escrito se muestra el progressbar
        CoroutineScope(Dispatchers.IO).launch { //creo una coroutine en el hilo secundario
            val myResponse = retrofit.create(ApiService::class.java).getSuperheroes(query) //variable que almacena la informacion que devuelve la api
            if (myResponse.isSuccessful){ //Si funciona y salio todo correcto
                val response: SuperHeroDataResponse? = myResponse.body() //almaceno en la variable el cuerpo de la respuesta que guarda toda la informacion del item
                if (response != null) { //si no es null
                    runOnUiThread { //indico que corra en el hilo principal
                        adapter.updateList(response.superheroes) //actualizo la lista del adapter con los datos recibidos
                        binding.progressBar.isVisible = false //muestro el progressbar
                    }
                }
            }else { //si no sale todo correcto
                Log.i("AlexAlvarado","NOO FUNCIONA") //muestro mensaje
            }
        }
    }

    private fun getRetrofit():Retrofit{ //funcion que controla la conexion con la Api
        return Retrofit //devuelvo el objeto retrofit creado
            .Builder()//configura y crea la instancia retrofit
            .baseUrl("https://superheroapi.com/") //url de la api
            .addConverterFactory(GsonConverterFactory.create()) //convertidor a formato gson
            .build()//obtiene el objeto configurado para usar


    }

    private fun navigateToDetail(id: String) { //funcion que inicia la ventana de informacion al presionar un item
        val intent = Intent(this, DetailSuperheroActivity::class.java) //creo un intent con el nuevo activity
        intent.putExtra(EXTRA_ID, id) //pasamos el id que esta selecinado
        startActivity(intent)//inciamos el nuevo activity
    }
}