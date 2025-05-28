package com.rocketquackit.meinebuchhaltung.data.items

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.rocketquackit.meinebuchhaltung.data.items_category.ItemsCategory
import com.rocketquackit.meinebuchhaltung.data.vat_rates.VatRates

@Entity(
    tableName = "items",
    foreignKeys = [
        ForeignKey(
            entity = ItemsCategory::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL // Optional: setzt Kategorie auf NULL, falls gelöscht
        ),
        ForeignKey(
            entity = VatRates::class,
            parentColumns = ["vatRateId"],
            childColumns = ["vatRateId"],
            onDelete = ForeignKey.SET_NULL // Optional: setzt VAT Rate auf NULL, falls gelöscht
        )
    ],
    indices = [
        Index(value = ["categoryId"]),
        Index(value = ["vatRateId"])
    ]
)
data class Items (

    // Artikel ID
    @PrimaryKey(autoGenerate = true)
    val articleId: Int = 0,

    // Artikel Daten allgemein
    val name: String,

    val description: String? = null,

    val price: Double,

    val categoryId: Int? = null,

    val vatRateId: Int? = null,


    // Artikel Einträge System Infos
    val createdAt: Long = System.currentTimeMillis(), // Zeitstempel für die Erstellung des Artikels
    val lastModifiedAt: Long = System.currentTimeMillis(), // Zeitstempel für die letzte Änderung des Artikels

)