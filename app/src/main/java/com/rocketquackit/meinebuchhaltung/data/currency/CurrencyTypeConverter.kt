package com.rocketquackit.meinebuchhaltung.data.currency

import androidx.room.TypeConverter
import com.rocketquackit.meinebuchhaltung.data.converter.EnumConverter

class CurrencyTypeConverter {

    private val converter = EnumConverter()

    @TypeConverter
    fun fromCurrencyType(value: CurrencyType?): String? {
        return converter.fromEnum(value)
    }

    @TypeConverter
    fun toCurrencyType(value: String?): CurrencyType? {
        return value?.let { CurrencyType.valueOf(it) }
    }

}