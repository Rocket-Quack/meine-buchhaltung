package com.rocketquackit.meinebuchhaltung.data.company

import com.rocketquackit.meinebuchhaltung.R

enum class CompanyType(val displayName: String) {
    Einzelunternehmen(R.string.company_type_Einzelunternehmen.toString()),
    Freiberuflich(R.string.company_type_Freiberuflich.toString()),
    GbR(R.string.company_type_GbR.toString()),
    UG(R.string.company_type_UG.toString()),
    GmbH(R.string.company_type_GmbH.toString()),
}