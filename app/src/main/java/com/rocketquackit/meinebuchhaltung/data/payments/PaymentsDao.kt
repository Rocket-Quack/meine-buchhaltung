package com.rocketquackit.meinebuchhaltung.data.payments

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PaymentsDao {

    @Insert
    suspend fun insert(payment: Payments): Long

    @Update
    suspend fun update(payment: Payments)

    @Delete
    suspend fun delete(payment: Payments)

    @Query("SELECT * FROM payments WHERE invoiceId = :invoiceId")
    suspend fun getPaymentsForInvoice(invoiceId: Int): List<Payments>

    @Query("SELECT SUM(amount) FROM payments WHERE invoiceId = :invoiceId")
    suspend fun getTotalPaymentsForInvoice(invoiceId: Int): Double?

}