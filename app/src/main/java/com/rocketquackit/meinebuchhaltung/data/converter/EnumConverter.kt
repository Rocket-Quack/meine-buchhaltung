package com.rocketquackit.meinebuchhaltung.data.converter

import androidx.room.TypeConverter

class EnumConverter {
    @TypeConverter
    fun fromEnum(value: Enum<*>?): String? {
        return value?.name
    }

    @TypeConverter
    fun toEnum(value: String?, enumType: Class<out Enum<*>>): Enum<*>? {
        return value?.let { enumValue ->
            java.lang.Enum.valueOf(enumType, enumValue)
        }
    }
}