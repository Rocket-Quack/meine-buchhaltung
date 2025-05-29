package com.rocketquackit.meinebuchhaltung.data.accounts

enum class AccountType(val number: String, val label: String, val type: String) {

    CASH("1000", "Kasse", "Aktiv"),
    BANK("1200", "Bank", "Aktiv"),
    SALES_REVENUE("8400", "Erlöse", "Ertrag"),
    VAT_PAYABLE("1776", "Umsatzsteuer", "Passiv")

}