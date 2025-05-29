package com.rocketquackit.meinebuchhaltung.data.invoice_items

data class InvoiceItemsSum(

    val netAmount: Double?, // Summe aller Netto-Beträge
    val vatAmount: Double?, // Summe aller VAT-Beträge
    val grossAmount: Double? // Summe aller Brutto-Beträge

)
