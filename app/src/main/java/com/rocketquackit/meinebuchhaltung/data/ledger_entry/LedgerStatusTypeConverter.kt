package com.rocketquackit.meinebuchhaltung.data.ledger_entry

import androidx.room.TypeConverter
import com.rocketquackit.meinebuchhaltung.data.converter.EnumConverter

class LedgerStatusTypeConverter {

    private val converter = EnumConverter()

    @TypeConverter
    fun fromLedgerStatusType(value: LedgerStatusType?): String? {
        return converter.fromEnum(value)
    }

    @TypeConverter
    fun toLedgerStatusType(value: String?): LedgerStatusType? {
        return value?.let { LedgerStatusType.valueOf(it) }
    }

}