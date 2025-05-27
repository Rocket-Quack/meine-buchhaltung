package com.rocketquackit.meinebuchhaltung.data.global

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rocketquackit.meinebuchhaltung.data.company.Company
import com.rocketquackit.meinebuchhaltung.data.company.CompanyDao

// 1) Übersicht aller Companies
@Database(
    entities = [Company::class],
    version = 1,
    exportSchema = true
)
abstract class AllCompaniesDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao

    companion object {
        @Volatile private var INSTANCE: AllCompaniesDatabase? = null

        fun getInstance(context: Context): AllCompaniesDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AllCompaniesDatabase::class.java,
                    "all_companies.db"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
