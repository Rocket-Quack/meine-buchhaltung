package com.rocketquackit.meinebuchhaltung.data.customer

import androidx.room.*

/**
 * DAO = Data Access Object
 * Diese Schnittstelle erlaubt den Zugriff auf die Customer-Tabelle.
 */
@Dao
interface CustomerDao {

    // Kunden speichern (insert oder update)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(customer: Customer)

    // Kunden löschen
    @Delete
    suspend fun delete(customer: Customer)

    // Alle Kunden laden
    @Query("SELECT * FROM customers")
    suspend fun getAll(): List<Customer>

    // Von einem Kunden den Offenen Betrag ausgeben
    @Query("SELECT * FROM customers WHERE outstandingAmount > 0")
    suspend fun getCustomersWithOutstandingAmounts(): List<Customer>

    // Kunden nach Offenen Betrag sortieren
    @Query("SELECT * FROM customers ORDER BY outstandingAmount DESC")
    suspend fun getCustomersSortedByOutstandingAmount(): List<Customer>

    // Gesamtbetrag der noch offen ist aller Kunden ausgeben
    @Query("SELECT SUM(outstandingAmount) FROM customers")
    suspend fun getTotalOutstandingAmount(): Long?

    // Kunden suchen
    @Query("SELECT * FROM customers WHERE name LIKE '%' || :search || '%' OR companyName LIKE '%' || :search || '%'")
    suspend fun searchCustomers(search: String): List<Customer>

    // Anzahl der Kunden ausgeben
    @Query("SELECT COUNT(*) FROM customers")
    suspend fun getCustomerCount(): Int
}
