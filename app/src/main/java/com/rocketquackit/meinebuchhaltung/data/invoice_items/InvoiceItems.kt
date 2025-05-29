package com.rocketquackit.meinebuchhaltung.data.invoice_items

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.rocketquackit.meinebuchhaltung.data.invoice.Invoice
import com.rocketquackit.meinebuchhaltung.data.vat_rates.VatRates

@Entity(
    tableName = "invoice_items",
    foreignKeys = [
        ForeignKey(
            entity = VatRates::class,
            parentColumns = ["vatRateId"],
            childColumns = ["vatRateId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Invoice::class,
            parentColumns = ["invoiceId"],
            childColumns = ["invoiceId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["invoiceId"]),
        Index(value = ["vatRateId"])
    ]
)
data class InvoiceItems(

    @PrimaryKey(autoGenerate = true)
    val itemId: Int = 0,

    val invoiceId: Int,      // Verweis auf die zugehörige Rechnung
    val articleId: Int,     // Verweis auf den Artikel

    val name: String,        // Name des Artikels/Postens
    val quantity: Double,    // Menge
    val unitPrice: Double,   // Einzelpreis Netto
    val totalNet: Double,    // Netto-Betrag für diese Position (quantity * unitPrice)

    val vatRateId: Int?,     // Verweis auf die VAT-Rate
    val vatAmount: Double,   // Berechnete MwSt. für diese Position
    val totalGross: Double   // Gesamtbetrag Brutto (totalNet + vatAmount)

)
