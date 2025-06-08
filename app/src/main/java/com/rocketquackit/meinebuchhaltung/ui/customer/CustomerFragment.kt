package com.rocketquackit.meinebuchhaltung.ui.customer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.l4digital.fastscroll.FastScrollRecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.DatabaseProvider
import com.rocketquackit.meinebuchhaltung.data.company.CompanyDatabase
import com.rocketquackit.meinebuchhaltung.data.customer.Customer
import com.rocketquackit.meinebuchhaltung.ui.customer.wizard.create.CreateCustomerActivity
import kotlinx.coroutines.launch

/**
 * Fragment zur Anzeige und Verwaltung der Kunden einer Firma.
 * Die Kundenliste wird reaktiv über ein ViewModel mit Flow aktualisiert.
 */
class CustomerFragment : Fragment() {

    // Datenbank-Instanz für die aktuell aktive Firma (wird für andere Operationen benötigt)
    private lateinit var companyDb: CompanyDatabase

    // Firmenname der aktuell ausgewählten Firma (aus SharedPreferences geladen)
    private lateinit var companyName: String

    // RecyclerView-Komponenten zur Darstellung der Kundenliste
    private lateinit var recyclerView: FastScrollRecyclerView
    private lateinit var adapter: CustomerAdapter

    // ViewModel zur Bereitstellung der Kundenliste als Flow
    private val viewModel: CustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Auslesen des aktuellen Firmennamens aus SharedPreferences
        val prefs = requireContext().getSharedPreferences("firma_prefs", Context.MODE_PRIVATE)
        companyName = prefs.getString("aktiveFirma", null)
            ?: throw IllegalStateException("Keine aktiveFirma in SharedPreferences gefunden")

        // Datenbankinstanz für diese Firma laden
        companyDb = DatabaseProvider.getCompanyDb(requireContext(), companyName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // XML-Layout des Fragments laden
        val view = inflater.inflate(R.layout.fragment_customer, container, false)

        // UI-Komponenten initialisieren
        initRecyclerView(view)
        initFab(view)

        // Beobachten der Kundenliste via Flow aus dem ViewModel
        observeCustomerList()

        return view
    }

    /**
     * Beobachtet den Flow `customers` aus dem ViewModel.
     * Immer wenn sich die Liste ändert, wird der RecyclerView aktualisiert.
     */
    private fun observeCustomerList() {
        lifecycleScope.launch {
            // Beobachtung nur aktiv, wenn das Fragment im sichtbaren Lifecycle-State ist
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.customers.collect { newList ->
                    adapter.submitList(newList) // DiffUtil wird hier verwendet um geänderte Einträge zu aktualisieren
                }
            }
        }
    }

    /**
     * Setzt den RecyclerView mit Linearlayout und bindet den Adapter an.
     */
    private fun initRecyclerView(root: View) {
        recyclerView = root.findViewById(R.id.recycler_customers)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CustomerAdapter()
        recyclerView.adapter = adapter
    }

    /**
     * Setzt die Aktion für den FloatingActionButton (Plus-Button).
     * Öffnet die Activity, in der ein neuer Kunde angelegt werden kann (Wizard).
     */
    private fun initFab(root: View) {
        val fab = root.findViewById<FloatingActionButton>(R.id.button_add_customer)
        fab.setOnClickListener {
            val intent = Intent(requireContext(), CreateCustomerActivity::class.java)
            startActivity(intent)
        }
    }
}
