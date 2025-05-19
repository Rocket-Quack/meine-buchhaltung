package com.rocketquackit.meinebuchhaltung.data.company

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rocketquackit.meinebuchhaltung.ui.customer.Customer
import com.rocketquackit.meinebuchhaltung.ui.customer.CustomerDao

/**
 * Dies ist die zentrale Room-Datenbankklasse für die App.
 * Hier werden alle Datenklassen (Entities) registrierst und Zugriff auf ihre DAOs gemanaged.
 *
 * Jede Firma bekommt ihre eigene Datenbank-Datei, z. B. "firma_Meier.db"
 */

// @Database → definiert, welche Tabellen (Entities) und Version verwendet werden
@Database(entities = [Company::class, Customer::class], version = 1)
abstract class CompanyDatabase : RoomDatabase() {

    // Zugriff auf die Tabelle "firmen"
    abstract fun firmaDao(): CompanyDao

    // Zugriff auf die Tabelle "customers"
    abstract fun customerDao(): CustomerDao

    companion object {
        /**
         * Diese Methode liefert die Firmen-Datenbank, basierend auf einem dynamischen Namen.
         *
         * @param context - der Anwendungskontext (z. B. Activity oder Fragment)
         * @param dbName - der Name der Datenbankdatei, z. B. "firma_Meier.db"
         * @return eine Instanz von FirmaDatabase, bereit zur Verwendung
         */
        fun getDatabase(context: Context, dbName: String): CompanyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CompanyDatabase::class.java,
                dbName
            ).build()
        }
    }
}
