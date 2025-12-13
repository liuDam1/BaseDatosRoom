package es.etg.dam.pmdm.basedatosroom.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ClienteTelefonosEntity(
    @Embedded val cliente: ClienteEntity,
    @Relation(
        parentColumn = "id", //Entidad cliente
        entityColumn = "cliente" // Entidad secundaria: telefono

    )
    val telefonos: List<TelefonoEntity>

)
