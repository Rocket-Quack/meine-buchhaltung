package com.rocketquackit.meinebuchhaltung.data.currency

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Diese Klasse stellt die möglichen Währungen dar
 */
@Entity(tableName = "currencies")
data class Currency(

    @PrimaryKey val code: String,  // z.B. "EUR", "USD"
    val currency_name: String,     // z.B. "Euro", "US Dollar"
    val symbol: String             // z.B. "€", "$"

)
