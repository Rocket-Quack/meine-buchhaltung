package com.rocketquackit.meinebuchhaltung.data.payments

import androidx.room.TypeConverter

class PaymentMethodTypeConverter {

    @TypeConverter
    fun fromPaymentMethod(method: PaymentMethodType): String {
        return method.name
    }

    @TypeConverter
    fun toPaymentMethod(name: String): PaymentMethodType {
        return PaymentMethodType.valueOf(name)
    }

}