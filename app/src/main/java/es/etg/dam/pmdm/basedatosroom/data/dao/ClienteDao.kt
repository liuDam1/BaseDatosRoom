package es.etg.dam.pmdm.basedatosroom.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import es.etg.dam.pmdm.basedatosroom.data.entity.ClienteEntity
import es.etg.dam.pmdm.basedatosroom.data.entity.ClienteTelefonosEntity

@Dao
interface ClienteDao {

    @Transaction
    @Query("SELECT * FROM cliente")
    suspend fun getClientesTelefonos(): List<ClienteTelefonosEntity>


    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: ClienteEntity):Long

    @Query ("SELECT id, nombre, apellidos FROM cliente")
    suspend fun getAll(): List<ClienteEntity>
}