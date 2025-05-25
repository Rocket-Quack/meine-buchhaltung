package com.rocketquackit.meinebuchhaltung.data.company

import androidx.room.TypeConverter
import com.rocketquackit.meinebuchhaltung.data.converter.EnumConverter

class CompanyCategoryConverter {

    private val converter = EnumConverter()

    @TypeConverter
    fun fromCompanyCategory(value: CompanyCategory?): String? {
        return converter.fromEnum(value)
    }

    @TypeConverter
    fun toCompanyCategory(value: String?): CompanyCategory? {
        return value?.let { CompanyCategory.valueOf(it) }
    }

}