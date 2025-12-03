package es.etg.dam.pmdm.basedatosroom.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ClienteEntity::class], version = 1)
abstract class ClienteDatabase: RoomDatabase() {

    abstract fun clienteDao(): ClienteDao
}