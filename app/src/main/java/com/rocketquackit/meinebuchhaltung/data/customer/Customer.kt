package com.rocketquackit.meinebuchhaltung.data.customer

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

/**
 * Diese Klasse stellt einen Kunden dar, der in einer Room-Datenbank gespeichert wird.
 * Jeder Kunde bekommt eine eindeutige ID.
 */
@Entity(
    tableName = "customers",
    indices = [
        Index(value = ["email"], unique = true),     // Index auf Emails
        Index(value = ["name"]),                     // Index auf Namen
        Index(value = ["companyName"])               // Index auf Firmennamen
    ]
)
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // ID wird automatisch vergeben

    // Kunde Allgemeine Daten
    val name: String, // Ansprechpartner beim Kunden oder Kundenname selber
    val companyLogo: ByteArray? = null, // Logo des Kunden (optional ansonsten Null))
    val companyName: String? = null, // Möglicher Kunde Unternehmennamen
    val street: String? = null,
    val houseNumber: String? = null,
    val zipCode: String? = null,
    val city: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val website: String? = null,

    // Kontodaten des Kunden
    val iban: String? = null, // IBAN des Kunden
    val bic: String? = null, // BIC des Kunden

    // Steuerdaten des Kunden
    val vatNumber: String? = null, // Umsatzsteueridentifikationsnummer
    val taxNumber: String? = null, // Steueridentifikationsnummer

    // Noch offener Betrag des Kunden
    val outstandingAmount: Double = 0.0, // Offener Betrag von unbezahlten Rechnungen

    val createdAt: Long = System.currentTimeMillis(), // Zeitstempel für die Erstellung des Kunden
    val lastModifiedAt: Long = System.currentTimeMillis(), // Zeitstempel für die letzte Änderung des Kunden

)
