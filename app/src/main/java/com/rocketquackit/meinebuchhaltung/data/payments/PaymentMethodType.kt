package com.rocketquackit.meinebuchhaltung.data.payments

enum class PaymentMethodType(val displayName: String) {

    CASH("Barzahlung"),
    BANK_TRANSFER("Überweisung"),
    CARD("Kartenzahlung"),
    PAYPAL("PayPal"),
    OTHER("Andere")

}
