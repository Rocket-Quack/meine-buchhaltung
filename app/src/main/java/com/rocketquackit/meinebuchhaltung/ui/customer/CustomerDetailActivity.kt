package com.rocketquackit.meinebuchhaltung.ui.customer

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.DatabaseProvider
import com.rocketquackit.meinebuchhaltung.data.customer.Customer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomerDetailActivity : AppCompatActivity() {

    private lateinit var imageLogo: ImageView
    private lateinit var textName: TextView
    private lateinit var textCompany: TextView
    private lateinit var textInvoiceCount: TextView
    private lateinit var textOutstanding: TextView
    private lateinit var textTotal: TextView
    private lateinit var textEmail: TextView
    private lateinit var textPhone: TextView
    private lateinit var textAddress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_customer_detail)

        // View initialisieren
        imageLogo = findViewById(R.id.image_logo)
        textName = findViewById(R.id.text_name)
        textCompany = findViewById(R.id.text_company)
        textInvoiceCount = findViewById(R.id.text_invoice_count)
        textOutstanding = findViewById(R.id.text_outstanding)
        textTotal = findViewById(R.id.text_total_value)
        textEmail = findViewById(R.id.text_email)
        textPhone = findViewById(R.id.text_phone)
        textAddress = findViewById(R.id.text_address)

        // ID aus Intent holen
        val customerId = intent.getIntExtra("customer_id", -1)
        if (customerId == -1) {
            finish()
            return
        }

        // Kundendaten laden
        CoroutineScope(Dispatchers.IO).launch {
            val companyName = getSharedPreferences("firma_prefs", MODE_PRIVATE)
                .getString("aktiveFirma", null) ?: return@launch

            val db = DatabaseProvider.getCompanyDb(applicationContext, companyName)
            val customer = db.customerDao().getCustomerById(customerId)

            customer?.let {
                withContext(Dispatchers.Main) {
                    populateUi(it)
                }
            }
        }
    }

    private fun populateUi(customer: Customer) {
        textName.text = customer.name
        textCompany.text = customer.companyName ?: ""

        textEmail.text = "E-Mail: ${customer.email.orEmpty()}"
        textPhone.text = "Telefon: ${customer.phoneNumber.orEmpty()}"
        textAddress.text = "Adresse: ${customer.street.orEmpty()} ${customer.houseNumber.orEmpty()}, ${customer.zipCode.orEmpty()} ${customer.city.orEmpty()}"

        if (customer.companyLogo != null) {
            val bmp = BitmapFactory.decodeByteArray(customer.companyLogo, 0, customer.companyLogo.size)
            imageLogo.setImageBitmap(bmp)
        } else {
            imageLogo.setImageResource(R.drawable.company)
        }

        // Statistiken (Platzhalter, bis Rechnungen angebunden sind)
        textInvoiceCount.text = "Rechnungen: –"
        textOutstanding.text = "Offen: –"
        textTotal.text = "0,00 €"
    }
}
