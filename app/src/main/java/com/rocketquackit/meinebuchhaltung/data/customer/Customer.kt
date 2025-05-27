package com.rocketquackit.meinebuchhaltung.data.customer

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Diese Klasse stellt einen Kunden dar, der in einer Room-Datenbank gespeichert wird.
 * Jeder Kunde bekommt eine eindeutige ID.
 */
@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // ID wird automatisch vergeben
    val name: String,
    val companyName: String,
    val street: String,
    val houseNumber: String,
    val zipCode: String,
    val city: String,
    val email: String
)
