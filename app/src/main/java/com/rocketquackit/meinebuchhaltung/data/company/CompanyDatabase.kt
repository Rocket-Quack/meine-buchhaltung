package com.rocketquackit.meinebuchhaltung.data.company

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rocketquackit.meinebuchhaltung.ui.customer.Customer
import com.rocketquackit.meinebuchhaltung.ui.customer.CustomerDao

// Datenbank für eine einzelne angelegte Firma
@Database(
    entities = [Customer::class, /*, … weitere Entities */],
    version = 1,
    exportSchema = false
)
abstract class CompanyDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao

    companion object {
        @Volatile private var INSTANCES: MutableMap<String, CompanyDatabase> = mutableMapOf()

        fun getInstance(context: Context, databaseName: String): CompanyDatabase =
            INSTANCES.getOrPut(databaseName) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CompanyDatabase::class.java,
                    databaseName
                ).build()
            }
    }
}

