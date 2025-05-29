package com.rocketquackit.meinebuchhaltung.data.payments

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.rocketquackit.meinebuchhaltung.data.invoice.Invoice

@Entity(
    tableName = "payments",
    foreignKeys = [
        ForeignKey(
            entity = Invoice::class,
            parentColumns = ["invoiceId"],
            childColumns = ["invoiceId"],
            onDelete = ForeignKey.CASCADE // Wenn Invoice gelöscht wird, auch Zahlungen löschen
        )
    ],
    indices = [
        Index(value = ["invoiceId"])
    ]
)
data class Payments(

    @PrimaryKey(autoGenerate = true)
    val paymentId: Int = 0, // Eindeutige ID der Zahlung

    val invoiceId: Int, // Verweis auf die zugehörige Rechnung
    val amount: Double, // Zahlungsbetrag
    val paymentDate: Long = System.currentTimeMillis(), // Zeitpunkt der Zahlung (Epoch-Zeit)
    val paymentMethod: String? = null, // Optional: Zahlungsmethode (z.B. Überweisung, Kreditkarte)
    val note: String? = null // Optional: Anmerkungen zur Zahlung

)
