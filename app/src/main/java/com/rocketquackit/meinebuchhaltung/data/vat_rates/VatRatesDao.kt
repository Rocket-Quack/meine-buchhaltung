package com.rocketquackit.meinebuchhaltung.data.vat_rates

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * DAO = Data Access Object
 * Diese Schnittstelle erlaubt den Zugriff auf die vat_rates-Tabelle.
 */
@Dao
interface VatRatesDao {

    // Einfügen einer neuen VAT Rate
    @Insert
    suspend fun insertVatRate(vatRates: VatRates): Long

    // Abrufen aller VAT Raten
    @Query("SELECT * FROM vat_rates")
    suspend fun getAllVatRates(): List<VatRates>

    // VAT Rate nach ID abrufen
    @Query("SELECT * FROM vat_rates WHERE vatRateId = :id")
    suspend fun getVatRateById(id: Int): VatRates?

    // VAT Rate aktualisieren
    @Update
    suspend fun updateVatRate(vatRate: VatRates)

    // VAT Rate löschen
    @Delete
    suspend fun deleteVatRate(vatRate: VatRates)

}