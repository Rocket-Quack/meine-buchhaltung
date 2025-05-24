package com.rocketquackit.meinebuchhaltung.data.company

import androidx.room.TypeConverter
import com.rocketquackit.meinebuchhaltung.data.converter.EnumConverter

class CompanyTypeConverter {

    private val converter = EnumConverter()

    @TypeConverter
    fun fromBusinessType(value: CompanyType?): String? {
        return converter.fromEnum(value)
    }

    @TypeConverter
    fun toBusinessType(value: String?): CompanyType? {
        return value?.let { CompanyType.valueOf(it) }
    }
}