package com.rocketquackit.meinebuchhaltung.data.currency

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): List<Currency>

    @Query("SELECT * FROM currencies WHERE code = :code")
    fun getCurrencyByCode(code: String): Currency?
}