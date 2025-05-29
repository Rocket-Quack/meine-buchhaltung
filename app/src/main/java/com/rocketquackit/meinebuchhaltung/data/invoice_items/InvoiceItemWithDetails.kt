package com.rocketquackit.meinebuchhaltung.data.invoice_items

import androidx.room.Embedded
import androidx.room.Relation
import com.rocketquackit.meinebuchhaltung.data.items.Items

// Diese Datenklasse verbindet ein InvoiceItem (Rechnungsposition) mit den zugehörigen Artikeldetails (Items)
data class InvoiceItemWithDetails(

    @Embedded val invoiceItem: InvoiceItems, // Das eigentliche Rechnungsitem

    @Relation(
        parentColumn = "articleId",
        entityColumn = "articleId"
    )
    val item: Items? // Der zugehörige Artikel aus der Items-Tabelle (kann null sein, falls nicht mehr existiert)
)
