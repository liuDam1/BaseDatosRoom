package es.etg.dam.pmdm.basedatosroom.ui

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import es.etg.dam.pmdm.basedatosroom.data.ClienteDatabase
import es.etg.dam.pmdm.basedatosroom.data.entity.ClienteEntity
import es.etg.dam.pmdm.basedatosroom.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        lateinit var database: ClienteDatabase
        const val DATABASE_NAME = "clietne"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainActivity.database =  Room.databaseBuilder(this, ClienteDatabase::class.java,
            DATABASE_NAME).build()
    }

    fun guardar(view: View){

        val nombre: String = binding.ettNombre.text.toString()
        val apellidos: String = binding.ettApellido.text.toString()
        val cliente = ClienteEntity(0, nombre, apellidos);
        val clienteDao = database.clienteDao()

        CoroutineScope(Dispatchers.IO).launch {
            clienteDao.insert(cliente)
        }
    }

    fun mostrar(view: View){
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, ArrayList<String>())
        val lv: ListView = binding.lvListado

        lv.adapter = adapter
        loadData(adapter)


    }

    fun  loadData(adapter: ArrayAdapter<String>) {
        val datos = ArrayList<String>()
        //En el disppatcher IO es para entradas y salidas: bases de datos, ficheros, redes...
        CoroutineScope(Dispatchers.IO).launch {
            val clienteDao = database.clienteDao()
            val clientes = clienteDao.getAll()
            clientes.forEach { cliente ->
                datos.add("Nombre ${cliente.nombre} y apellidos ${cliente.apellidos}")
            }
            //Lo siguiente, que es un actualización de la vista, lo ejecutamos en el hilo principal
            //Cambiamos el contexto
            withContext(Dispatchers.Main) {
                adapter.addAll(datos)
                adapter.notifyDataSetChanged()
            }
        }
    }
}