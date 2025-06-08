package com.rocketquackit.meinebuchhaltung.ui.customer.invoices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rocketquackit.meinebuchhaltung.R

class CustomerInvoicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_invoices)

        val customerId = intent.getIntExtra("customer_id", -1)
        if (customerId == -1) {
            finish()
            return
        }

        // Lade hier z. B. alle Rechnungen aus der Datenbank über customerId
    }
}
