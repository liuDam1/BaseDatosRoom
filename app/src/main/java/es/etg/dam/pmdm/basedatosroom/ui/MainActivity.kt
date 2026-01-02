package es.etg.dam.pmdm.basedatosroom.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import es.etg.dam.pmdm.basedatosroom.data.ClienteDatabase
import es.etg.dam.pmdm.basedatosroom.data.entity.ClienteEntity
import es.etg.dam.pmdm.basedatosroom.data.entity.ClienteTelefonosEntity
import es.etg.dam.pmdm.basedatosroom.data.entity.TelefonoEntity
import es.etg.dam.pmdm.basedatosroom.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        lateinit var database: ClienteDatabase
        const val DATABASE_NAME = "cliente"
        const val MSG_COMPLETAR = "Por favor complete todos los campos"
        const val MSG_ALMACENADO = "Almacenado corectamente"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainActivity.database =  Room.databaseBuilder(this,
            ClienteDatabase::class.java,
            DATABASE_NAME).build()

    }

    fun guardar(view: View){
        val clienteDao = database.clienteDao()
        val telefonoDao = database.telefonoDao();

        var nombre = binding.ettNombre.text.toString()
        var apellido = binding.ettApellido.text.toString()
        var numTelefono = binding.ettTelefono.text.toString()

        if (nombre.isEmpty() || apellido.isEmpty() || numTelefono.isEmpty()) {
            Toast.makeText(this, MSG_COMPLETAR, Toast.LENGTH_SHORT).show()
            return
        }

        var clienteId: Long = 0 ;
        val cliente = ClienteEntity(0, nombre, apellido);
        var lista: List<ClienteTelefonosEntity>

        CoroutineScope(Dispatchers.IO).launch {

            // Insertamos el cliente y nos guardamos su id
            clienteId = clienteDao.insert(cliente)
            // Creamos un teléfono para ese cliente (con el id antes recogido)
            val telefono = TelefonoEntity(0, numTelefono, clienteId)
            // Insertamos el cliente
            telefonoDao.insert(telefono)

            // Obtenemos todos los clientes y sus telefonos
            lista = clienteDao.getClientesTelefonos();
        }

        Toast.makeText(this,MSG_ALMACENADO,Toast.LENGTH_LONG).show()
        limpiar()
    }

    private fun limpiar() {
        binding.ettNombre.text.clear()
        binding.ettApellido.text.clear()
        binding.ettTelefono.text.clear()
    }

}