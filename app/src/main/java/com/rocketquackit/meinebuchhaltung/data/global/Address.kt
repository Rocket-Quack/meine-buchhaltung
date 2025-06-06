package com.rocketquackit.meinebuchhaltung.data.global

import androidx.room.ColumnInfo

data class Address(
    @ColumnInfo(name = "street") val street: String,
    @ColumnInfo(name = "house_number") val houseNumber: String,
    @ColumnInfo(name = "postal_code") val postalCode: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "country") val country: String
)