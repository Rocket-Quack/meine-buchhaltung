package com.rocketquackit.meinebuchhaltung.ui.customer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.DatabaseProvider
import com.rocketquackit.meinebuchhaltung.data.company.CompanyDatabase
import com.rocketquackit.meinebuchhaltung.data.customer.Customer
import kotlinx.coroutines.launch

/**
 * Fragment zur Anzeige und Verwaltung der Kunden einer Firma.
 * Lädt alle Kunden aus der Datenbank und zeigt sie in einer RecyclerView.
 */
class CustomerFragment : Fragment() {

    // Datenbank-Instanz für die aktuell aktive Firma
    private lateinit var companyDb: CompanyDatabase

    // Name der aktiven Firma, geladen aus SharedPreferences
    private lateinit var companyName: String

    // RecyclerView und Adapter für die Anzeige der Kundenliste
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomerAdapter
    private val customerList: MutableList<Customer> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aktive Firma aus SharedPreferences auslesen
        val prefs = requireContext().getSharedPreferences("firma_prefs", Context.MODE_PRIVATE)
        companyName = prefs.getString("aktiveFirma", null)
            ?: throw IllegalStateException("Keine aktiveFirma in SharedPreferences gefunden")

        // Datenbank-Instanz initialisieren
        companyDb = DatabaseProvider.getCompanyDb(requireContext(), companyName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Layout für dieses Fragment laden
        val view = inflater.inflate(R.layout.fragment_customer, container, false)

        // RecyclerView und FloatingActionButton einrichten
        initRecyclerView(view)
        initFab(view)

        // Kunden aus DB laden
        loadCustomers()

        return view
    }

    /**
     * Initialisiert RecyclerView und ihren Adapter.
     */
    private fun initRecyclerView(root: View) {
        recyclerView = root.findViewById(R.id.recycler_customers)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CustomerAdapter(customerList)
        recyclerView.adapter = adapter
    }

    /**
     * Setzt den Klick-Listener für den "Kunden hinzufügen"-Button.
     */
    private fun initFab(root: View) {
        val fab = root.findViewById<FloatingActionButton>(R.id.button_add_customer)
        fab.setOnClickListener {
            addTestCustomer()
        }
    }

    /**
     * Fügt einen Beispiel-Kunden in die Datenbank ein und aktualisiert die Ansicht.
     */
    private fun addTestCustomer() {
        val customer = Customer(
            name = "Max Mustermann",
            companyName = companyName,
            street = "Hauptstraße",
            houseNumber = "12a",
            zipCode = "12345",
            city = "Musterstadt",
            email = "max@muster.de"
        )

        // DB-Operation im Hintergrund
        lifecycleScope.launch {
            companyDb.customerDao().insert(customer)
            loadCustomers()
        }
    }

    /**
     * Lädt alle Kunden aus der DB und benachrichtigt den Adapter über Änderungen.
     */
    private fun loadCustomers() {
        lifecycleScope.launch {
            val allCustomers = companyDb.customerDao().getAll()
            customerList.clear()
            customerList.addAll(allCustomers)
            adapter.notifyDataSetChanged()
        }
    }
}
