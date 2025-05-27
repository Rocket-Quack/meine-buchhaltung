package com.rocketquackit.meinebuchhaltung.data.invoice

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Diese Klasse stellt die Rechnungen dar
 * Jede Rechnung erhält eine eindeutige ID und die ID des zugeordneten Kunden.
 */
@Entity(
    tableName = "invoices",
    foreignKeys = [
        ForeignKey(
            entity = com.rocketquackit.meinebuchhaltung.data.customer.Customer::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Invoice(

    @PrimaryKey(autoGenerate = true) val id: Int = 0, // ID wird automatisch vergeben
    val customerId: Int, // ID des Kunden, zu dem die Rechnung gehört

    //TODO: weitere Attribute hinzufügen

    val createdAt: Long = System.currentTimeMillis(), // Zeitstempel für die Erstellung der Rechnung
)