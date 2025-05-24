package com.rocketquackit.meinebuchhaltung.data.company

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company")
data class Company(
    @PrimaryKey(autoGenerate = true) val companyId: Int = 0,
    val companyName: String, // Unternehmensnamen
    val businessType: String, // Unternehmensform (GmbH, UG)
    val taxNumber: String, // Steueridentifikationsnummer
    val vatNumber: String?, // Umsatzsteueridentifikationsnummer (optional)
    val registrationNumber: String?, // Registrierungsnummer und Register Gericht (optional)
    val address: String, // Adresse vom Unternehmen
    val phoneNumber: String?, // Optional Telefonnummer
    val email: String, // Haupt-E-Mail
    val website: String?, // Optional Webseite
    val companyBankIBAN: String?, // Kontonummer vom Unternehmen
    val companyBankBIC: String?, // Bankleitzahl vom Unternehmen
    val companyBankName: String?, // Bankname vom Unternehmen
    val companyLogo: ByteArray?, // Logo vom Unternehmen


    val createdAt: Long = System.currentTimeMillis(), // Zeitstempel für die Erstellung der Firma
    val lastModifiedAt: Long = System.currentTimeMillis(), // Zeitstempel für die letzte Änderung der Firma

)