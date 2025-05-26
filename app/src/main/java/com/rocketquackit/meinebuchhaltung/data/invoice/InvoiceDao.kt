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

}