package com.rocketquackit.meinebuchhaltung.data.invoice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InvoiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(invoice: Invoice)

    @Delete
    suspend fun delete(invoice: Invoice)

    @Query("SELECT * FROM invoices WHERE customerId = :customerId")
    suspend fun getInvoicesForCustomer(customerId: Int): List<Invoice>

    @Query("SELECT * FROM invoices")
    suspend fun getAll(): List<Invoice>

    // Aktualisiert die Rechnungsbeträge bei Hinzufügen von Artikeln
    @Query(
        """
        UPDATE invoices
        SET netAmount = :netAmount,
            vatAmount = :vatAmount,
            grossAmount = :grossAmount,
            totalAmount = :totalAmount
        WHERE invoiceId = :invoiceId
    """
    )
    suspend fun updateInvoiceTotals(
        invoiceId: Int,
        netAmount: Double,
        vatAmount: Double,
        grossAmount: Double,
        totalAmount: Double
    )

    // Aktualisiert die offenen Beträge (z.B. nach einer Zahlung)
    @Query(
        """
        UPDATE invoices
        SET outstandingAmount = :newOutstandingAmount
        WHERE invoiceId = :invoiceId
    """
    )
    suspend fun updateOutstandingAmount(invoiceId: Int, newOutstandingAmount: Double)

    // Aktualisiert offener BEtarg und den Status der Rechnung (z.B. "OPEN" -> "PAID")
    @Query(
        """
        UPDATE invoices
        SET outstandingAmount = :newOutstandingAmount,
            status = :newStatus
        WHERE invoiceId = :invoiceId
    """
    )
    suspend fun updateOutstandingAmountAndStatus(
        invoiceId: Int,
        newOutstandingAmount: Double,
        newStatus: InvoiceStatusType
    )

    // Den Status der Rechnung (z.B. "OPEN" -> "CANCELLED")
    @Query(
        """
        UPDATE invoices
        SET status = :newStatus
        WHERE invoiceId = :invoiceId
    """
    )
    suspend fun updateOutstandingAmountAndStatus(
        invoiceId: Int,
        newStatus: InvoiceStatusType
    )

}