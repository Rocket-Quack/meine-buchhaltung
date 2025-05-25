package com.rocketquackit.meinebuchhaltung.data

import android.content.Context
import com.rocketquackit.meinebuchhaltung.data.company.CompanyDatabase
import com.rocketquackit.meinebuchhaltung.data.global.AllCompaniesDatabase

object DatabaseProvider {

    /** pro-Firma DB, z. B. "company_Firma.db" */
    fun getCompanyDb(context: Context, companyName: String): CompanyDatabase {
        val dbName = "company_${companyName}.db"
        return CompanyDatabase.getInstance(context, dbName)
    }

    /** globaler Company-Übersicht-DB */
    fun getCompaniesDb(context: Context): AllCompaniesDatabase =
        AllCompaniesDatabase.getInstance(context)

}
