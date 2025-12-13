package es.etg.dam.pmdm.basedatosroom.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import es.etg.dam.pmdm.basedatosroom.data.entity.TelefonoEntity

@Dao
interface TelefonoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(telefono: TelefonoEntity)
}