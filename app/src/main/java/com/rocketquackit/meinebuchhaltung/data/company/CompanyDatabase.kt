package com.rocketquackit.meinebuchhaltung.data.company

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rocketquackit.meinebuchhaltung.data.currency.Currency
import com.rocketquackit.meinebuchhaltung.data.customer.Customer
import com.rocketquackit.meinebuchhaltung.data.customer.CustomerDao
import com.rocketquackit.meinebuchhaltung.data.invoice.Invoice

// Datenbank für eine einzelne angelegte Firma
@Database(
    entities = [
        Customer::class,
        Invoice::class,
        Currency::class
        // TODO Weitere Entities hinzufügen
        ],
    version = 1,
    exportSchema = true
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
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Initiale Currencies-Daten einfügen
                        val initialCurrencies = listOf(
                            Currency("EUR", "Euro", "€"),
                            Currency("USD", "US Dollar", "$"),
                        )
                        initialCurrencies.forEach { currency ->
                            db.execSQL(
                                "INSERT INTO currencies (code, currency_name, symbol) VALUES (?, ?, ?)",
                                arrayOf(currency.code, currency.currency_name, currency.symbol)
                            )
                        }
                    }
                }).build()
            }
    }
}

