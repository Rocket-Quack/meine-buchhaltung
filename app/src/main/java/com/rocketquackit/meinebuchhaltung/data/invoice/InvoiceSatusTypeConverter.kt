package com.rocketquackit.meinebuchhaltung.data.invoice

import androidx.room.TypeConverter
import com.rocketquackit.meinebuchhaltung.data.converter.EnumConverter

class InvoiceSatusTypeConverter {

    private val converter = EnumConverter()

    @TypeConverter
    fun fromInvoiceStatusType(value: InvoiceStatusType?): String? {
        return converter.fromEnum(value)
    }

    @TypeConverter
    fun toInvoiceStatusType(value: String?): InvoiceStatusType? {
        return value?.let { InvoiceStatusType.valueOf(it) }
    }

}