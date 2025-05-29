package com.rocketquackit.meinebuchhaltung.data.accounts

import androidx.room.TypeConverter
import com.rocketquackit.meinebuchhaltung.data.converter.EnumConverter

class AccountTypeConverter {

    private val converter = EnumConverter()

    @TypeConverter
    fun fromAccountType(value: AccountType?): String? {
        return converter.fromEnum(value)
    }

    @TypeConverter
    fun toAccountType(value: String?): AccountType? {
        return value?.let { AccountType.valueOf(it) }
    }

}

