package com.rocketquackit.meinebuchhaltung.data.company

enum class CompanyType(val displayName: String) {
    Einzelunternehmen("Einzelunternehmen"),
    Freiberuflich("Freiberuflich"),
    GbR("GbR"),
    UG("UG"),
    GmbH("GmbH"),
    GmbHCoKG("GmbH & Co. KG"),
    OHG("OHG")
}