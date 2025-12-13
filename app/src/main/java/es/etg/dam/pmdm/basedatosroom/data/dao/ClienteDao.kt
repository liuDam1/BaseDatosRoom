package es.etg.dam.pmdm.basedatosroom.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.etg.dam.pmdm.basedatosroom.data.entity.ClienteEntity

@Dao
interface ClienteDao {

    @Query("SELECT id, nombre, apellidos, vip FROM cliente")
    suspend fun getAll(): List<ClienteEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(cliente: ClienteEntity)

    @Delete
    suspend fun delete(cliente: ClienteEntity)
}