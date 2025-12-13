package es.etg.dam.pmdm.basedatosroom.data

import androidx.room.Database
import androidx.room.RoomDatabase
import es.etg.dam.pmdm.basedatosroom.data.dao.ClienteDao
import es.etg.dam.pmdm.basedatosroom.data.dao.TelefonoDao
import es.etg.dam.pmdm.basedatosroom.data.entity.ClienteEntity
import es.etg.dam.pmdm.basedatosroom.data.entity.TelefonoEntity

@Database(entities = [ClienteEntity::class, TelefonoEntity::class], version = 1)
abstract class ClienteDatabase: RoomDatabase() {

    abstract fun clienteDao(): ClienteDao

    abstract fun telefonoDao(): TelefonoDao
}