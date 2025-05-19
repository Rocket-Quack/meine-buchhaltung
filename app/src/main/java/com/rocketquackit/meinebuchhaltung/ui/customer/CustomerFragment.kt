package com.rocketquackit.meinebuchhaltung.ui.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.DatabaseProvider
import kotlinx.coroutines.launch

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomerAdapter
    private val customerList = mutableListOf<Customer>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_customer, container, false)

        // RecyclerView einrichten
        recyclerView = view.findViewById(R.id.recycler_customers)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CustomerAdapter(customerList)
        recyclerView.adapter = adapter

        // Button einrichten
        val fab = view.findViewById<FloatingActionButton>(R.id.button_add_customer)
        fab.setOnClickListener {
            addTestCustomer()
        }

        loadCustomers()

        return view
    }

    private fun addTestCustomer() {
        // Erstelle neuen Kunden
        val customer = Customer(
            name = "Max Mustermann",
            companyName = "Muster GmbH",
            street = "Hauptstraße",
            houseNumber = "12a",
            zipCode = "12345",
            city = "Musterstadt",
            email = "max@muster.de"
        )

        // Zugriff auf die Datenbank der aktiven Firma → speichern
        lifecycleScope.launch {
            val db = DatabaseProvider.getFirmaDatabase(requireContext())
            db.customerDao().insert(customer)
            loadCustomers() // danach neu laden
        }
    }

    private fun loadCustomers() {
        lifecycleScope.launch {
            // Zugriff auf DB der aktiven Firma
            val db = DatabaseProvider.getFirmaDatabase(requireContext())
            val allCustomers = db.customerDao().getAll() // Alle Kunden dieser DB
            customerList.clear()
            customerList.addAll(allCustomers)
            adapter.notifyDataSetChanged()
        }
    }
}