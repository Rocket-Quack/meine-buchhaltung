package com.rocketquackit.meinebuchhaltung.data.vat_rates

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "vat_rates"
)
data class VatRates(

    // ID für den Steuersatz
    @PrimaryKey(autoGenerate = true)
    val vatRateId: Int = 0,

    // Steuersatz Daten allgemein
    val name: String,
    val rate: Double,
    val description: String? = null // "Standardrate" oder "Ermäßigter Satz"
)
