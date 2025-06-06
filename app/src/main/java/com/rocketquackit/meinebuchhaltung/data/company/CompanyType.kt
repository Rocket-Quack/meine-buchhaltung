package com.rocketquackit.meinebuchhaltung.data.company

import android.content.Context
import com.rocketquackit.meinebuchhaltung.R

enum class CompanyType(val displayNameResId: Int) {
    Einzelunternehmen(R.string.company_type_Einzelunternehmen),
    Freiberuflich(R.string.company_type_Freiberuflich),
    GbR(R.string.company_type_GbR),
    UG(R.string.company_type_UG),
    GmbH(R.string.company_type_GmbH);

    fun getDisplayName(context: Context): String {
        return context.getString(displayNameResId)
    }

}