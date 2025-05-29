package com.rocketquackit.meinebuchhaltung.data.ledger_entry

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LedgerEntryDao {

    // Aktulaisieren des Status einer gewissen Buchung
    @Query("UPDATE ledger_entries SET status = :newStatus WHERE entryId = :entryId")
    suspend fun updateLedgerStatus(entryId: Int, newStatus: String)

}