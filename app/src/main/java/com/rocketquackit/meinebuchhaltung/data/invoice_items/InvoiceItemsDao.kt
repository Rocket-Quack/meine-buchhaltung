package com.rocketquackit.meinebuchhaltung.data.invoice_items

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface InvoiceItemsDao {

    // Summiert alle Positionen einer Rechnung (Netto, VAT, Brutto)
    @Query("""
    SELECT SUM(totalNet) AS netAmount, 
           SUM(vatAmount) AS vatAmount, 
           SUM(totalGross) AS grossAmount
    FROM invoice_items
    WHERE invoiceId = :invoiceId
""")
    suspend fun calculateInvoiceSums(invoiceId: Int): InvoiceItemsSum?

    // Holt alle Positionen einer Rechnung inkl. der Artikeldetails (Relation)
    @Transaction
    @Query("SELECT * FROM invoice_items WHERE invoiceId = :invoiceId")
    suspend fun getInvoiceItemsWithDetails(invoiceId: Int): List<InvoiceItemWithDetails>

}