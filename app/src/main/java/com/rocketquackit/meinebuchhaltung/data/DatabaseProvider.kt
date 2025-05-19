package com.rocketquackit.meinebuchhaltung.data

import android.content.Context
import com.rocketquackit.meinebuchhaltung.data.company.CompanyDatabase

object DatabaseProvider {

    fun getFirmaDatabase(context: Context): CompanyDatabase {
        val prefs = context.getSharedPreferences("firma_prefs", Context.MODE_PRIVATE)
        val aktiveFirma = prefs.getString("aktiveFirma", null)
            ?: throw IllegalStateException("Keine aktive Firma ausgewählt!")

        // Datenbankname zusammensetzen z. B. "firma_Musterfirma.db"
        val dbName = "firma_${aktiveFirma}.db"

        return CompanyDatabase.getDatabase(context, dbName)
    }
}
