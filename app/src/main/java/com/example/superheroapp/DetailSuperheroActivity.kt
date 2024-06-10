package com.example.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.example.superheroapp.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {
    companion object { //variable publica para todo el programa
        const val EXTRA_ID = "extra_id" //variable id que alamacena el valor al iniciar el intent
    }

    private lateinit var binding: ActivityDetailSuperheroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()//alamaceno el id del item seleccionado indica que si es null guarda una lista vacia
        getSuperheroInformation(id) //funcion que muestra la informacion del item
    }

    private fun getSuperheroInformation(id: String) { //funcion que muestra la informacion que recibe de internet del item seleccionado
        CoroutineScope(Dispatchers.IO).launch { //lanza una nueva coroutine
            val superheroDetail = //variable que almacena los datos recibidos de la Api
                getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)

            if(superheroDetail.body() != null){ //si lo que se recibe del cuerpo del item no esta vacio o no es nulo
                runOnUiThread { createUI(superheroDetail.body()!!) } //en el hilo principal se muestran la informacion
            }
        }
    }

    private fun createUI(superhero: SuperHeroDetailResponse) { //funcion que muestra la infomacion recibida en el layout
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero) //utiliza la biblioteca picasso para cargar el url de la imagen de internet
        binding.tvSuperheroName.text = superhero.name //asigna el valor recibido en el layout
        prepareStats(superhero.powerstats) //se envia la informacion de poder a la funcion para preparar la grafica
        binding.tvSuperheroRealName.text = superhero.biography.fullName//asigna el valor recibido en el layout
        binding.tvPublisher.text = superhero.biography.publisher//asigna el valor recibido en el layout
    }

    private fun prepareStats(powerstats: PowerStatsResponse) { //funcion que prepara la grafica
        updateHeight(binding.viewCombat, powerstats.combat) //calcula la altura para los puntos en combate recibido del item
        updateHeight(binding.viewDurability, powerstats.durability)//calcula la altura para los puntos em durabilidad recibido del item
        updateHeight(binding.viewSpeed, powerstats.speed)//calcula la altura para los puntos en velocidad recibido del item
        updateHeight(binding.viewStrength, powerstats.strength)//calcula la altura para los puntos en fortaleza recibido del item
        updateHeight(binding.viewIntelligence, powerstats.intelligence)//calcula la altura para los puntos en inteligencia recibido del item
        updateHeight(binding.viewPower, powerstats.power)//calcula la altura para los puntos en poder recibido del item
    }

    private fun updateHeight(view: View, stat:String){ //funcion que actualiza la altura con el view y la estadistica del item
        val params = view.layoutParams //variable que almacena los parametros del view
        params.height = pxToDp(stat.toFloat())//actualiza la altura pasando de pxl a dp
        view.layoutParams = params //asigna el nuevo parametro a la vista con el valor calculado
    }

    private fun pxToDp(px:Float):Int{ //funcion que convierte pxl a dp
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt() //devuelve el valor redondeado
    }

    private fun getRetrofit(): Retrofit { //funcion que controla la conexion con la Api
        return Retrofit //devuelve la conexion
            .Builder() //configura y crea la instancia retrofit
            .baseUrl("https://superheroapi.com/") //url de la api
            .addConverterFactory(GsonConverterFactory.create()) //convertidor a formato gson
            .build() //obtiene el objeto configurado para usar
    }
}