package com.rocketquackit.meinebuchhaltung.data.company

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rocketquackit.meinebuchhaltung.data.audit_trail.AuditTrail
import com.rocketquackit.meinebuchhaltung.data.audit_trail.AuditTrailDao
import com.rocketquackit.meinebuchhaltung.data.currency.Currency
import com.rocketquackit.meinebuchhaltung.data.currency.CurrencyDao
import com.rocketquackit.meinebuchhaltung.data.customer.Customer
import com.rocketquackit.meinebuchhaltung.data.customer.CustomerDao
import com.rocketquackit.meinebuchhaltung.data.invoice.Invoice
import com.rocketquackit.meinebuchhaltung.data.invoice.InvoiceDao
import com.rocketquackit.meinebuchhaltung.data.invoice_items.InvoiceItems
import com.rocketquackit.meinebuchhaltung.data.invoice_items.InvoiceItemsDao
import com.rocketquackit.meinebuchhaltung.data.items.Items
import com.rocketquackit.meinebuchhaltung.data.items.ItemsDao
import com.rocketquackit.meinebuchhaltung.data.items_category.ItemsCategory
import com.rocketquackit.meinebuchhaltung.data.items_category.ItemsCategoryDao
import com.rocketquackit.meinebuchhaltung.data.ledger_entry.LedgerEntry
import com.rocketquackit.meinebuchhaltung.data.ledger_entry.LedgerEntryDao
import com.rocketquackit.meinebuchhaltung.data.payments.Payments
import com.rocketquackit.meinebuchhaltung.data.payments.PaymentsDao
import com.rocketquackit.meinebuchhaltung.data.vat_rates.VatRates
import com.rocketquackit.meinebuchhaltung.data.vat_rates.VatRatesDao

// Datenbank für eine einzelne angelegte Firma und die benötigten Tabellen innerhalb dieser Firma
@Database(
    entities = [
        Customer::class,
        Invoice::class,
        InvoiceItems::class,
        VatRates::class,
        Currency::class,
        Items::class,
        ItemsCategory::class,
        Payments::class,
        LedgerEntry::class,
        AuditTrail::class,
        ],
    version = 1,
    exportSchema = true
)
abstract class CompanyDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun invoiceItemsDao(): InvoiceItemsDao
    abstract fun itemsDao(): ItemsDao
    abstract fun itemsCategoryDao(): ItemsCategoryDao
    abstract fun currencyDao(): CurrencyDao
    abstract fun paymentsDao(): PaymentsDao
    abstract fun ledgerEntryDao(): LedgerEntryDao
    abstract fun auditTrailDao(): AuditTrailDao
    abstract fun VatRatesDao(): VatRatesDao


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

