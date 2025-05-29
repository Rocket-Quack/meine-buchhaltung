package com.rocketquackit.meinebuchhaltung.data.ledger_entry

enum class LedgerStatusType(val displayName: String) {
    BOOKED("Gebucht"),
    CANCELED("Storniert"),
    DRAFT("Entwurf")
}