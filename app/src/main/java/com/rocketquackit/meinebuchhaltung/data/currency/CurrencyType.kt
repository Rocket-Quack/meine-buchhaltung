package com.rocketquackit.meinebuchhaltung.data.currency

enum class CurrencyType(val code: String, val currency_name: String, val symbol: String) {
    EUR("EUR", "Euro", "€"),
    USD("USD", "US Dollar", "$"),
}