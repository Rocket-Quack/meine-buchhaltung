package com.rocketquackit.meinebuchhaltung.data.invoice

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

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
        ),
        ForeignKey(
            entity = com.rocketquackit.meinebuchhaltung.data.currency.Currency::class,
            parentColumns = ["code"],
            childColumns = ["currency"],
            onDelete = ForeignKey.RESTRICT // Währung soll beibehalten werden nur Referenzieren
        )
    ]
)
data class Invoice(

    // RechnungsID
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // ID der Invoice - > Wird automatisch vergeben
    val customerId: Int, // ID des Kunden, zu dem die Rechnung gehört

    // Rechnungsdaten allgemein
    var invoiceNumber: String, // Rechnungsnummer (Number-Series)
    val referenceNumber: String? = null, // Referenznummer (optional)

    // Rechnungsbetrag
    val netAmount: Double, // Nettobetrag
    val vatAmount: Double, // Mehrwertsteuer
    val grossAmount: Double, // Bruttobetrag
    @ColumnInfo(index = true) val currency: String, // // Währung Fremdschlüssel auf currencies

    // Rabatt
    val discountAmount: Double, // Rabattbetrag
    val discountPercentage: Double, // Rabatt in Prozent

    // Betrag und Restbetrag
    val totalAmount: Double, // Gesamtbetrag
    val outstandingAmount: Double, // Restbetrag

    // Status und Zahlungsziel
    val invoiceDate: String, // Rechnungsdatum
    val dueDate: String, // Fälligkeitsdatum
    @TypeConverters(InvoiceSatusTypeConverter::class)
    val status: InvoiceStatusType, // Status der Rechnung (offen, bezahlt, abbezahlt)

    // System Daten für die Rechnung
    val createdAt: Long = System.currentTimeMillis(), // Zeitstempel für die Erstellung der Rechnung
)