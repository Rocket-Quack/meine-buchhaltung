package com.rocketquackit.meinebuchhaltung.data.customer

import androidx.room.*

/**
 * DAO = Data Access Object
 * Diese Schnittstelle erlaubt den Zugriff auf die Customer-Tabelle.
 */
@Dao
interface CustomerDao {

    // Kunden speichern (insert oder update)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: Customer)

    // Kunden löschen
    @Delete
    suspend fun delete(customer: Customer)

    // Alle Kunden laden
    @Query("SELECT * FROM customers")
    suspend fun getAll(): List<Customer>
}
