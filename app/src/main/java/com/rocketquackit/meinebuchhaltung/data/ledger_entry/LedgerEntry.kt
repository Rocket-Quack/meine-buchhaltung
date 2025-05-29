package com.rocketquackit.meinebuchhaltung.data.ledger_entry

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rocketquackit.meinebuchhaltung.data.accounts.AccountType
import com.rocketquackit.meinebuchhaltung.data.accounts.AccountTypeConverter


@Entity(tableName = "ledger_entries")
@TypeConverters(
    AccountTypeConverter::class,
    LedgerStatusTypeConverter::class
)
data class LedgerEntry(
    @PrimaryKey(autoGenerate = true)
    val entryId: Int = 0,

    val date: Long = System.currentTimeMillis(), // Zeitpunkt der Buchung
    val description: String,                     // Beschreibung
    val amount: Double,                          // Betrag

    val debitAccountType: AccountType,           // Konten als Enum
    val creditAccountType: AccountType,          // Konten als Enum

    val referenceId: Int? = null,                // Optional: Verweis auf Rechnung/Zahlung
    val referenceType: String? = null,           // Optional: Typ der Referenz

    val status: LedgerStatusType = LedgerStatusType.DRAFT // Status der Buchung zuerst auf Draft
)