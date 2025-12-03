package es.etg.dam.pmdm.basedatosroom

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import es.etg.dam.pmdm.basedatosroom.data.ClienteDatabase
import es.etg.dam.pmdm.basedatosroom.data.ClienteEntity
import es.etg.dam.pmdm.basedatosroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        lateinit var database: ClienteDatabase
        const val DATABASE_NAME = "clietne-db"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainActivity.database =  Room.databaseBuilder(this, ClienteDatabase::class.java,
            DATABASE_NAME).build()
    }

    suspend fun guardar(view: View){

        val nombre: String = binding.ettNombre.text.toString()
        val apellidos: String = binding.ettApellido.text.toString()
        val cliente = ClienteEntity(0, nombre, apellidos);
        val clienteDao = database.clienteDao()

        clienteDao.insert(cliente)
    }
}