package com.rocketquackit.meinebuchhaltung.ui.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rocketquackit.meinebuchhaltung.R

// TODO: Rename parameter arguments, choose names that match
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

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CustomerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CustomerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun addTestCustomer() {
        val customer = Customer(
            name = "Max Mustermann",
            companyName = "Muster GmbH",
            street = "Hauptstraße",
            houseNumber = "12a",
            zipCode = "12345",
            city = "Musterstadt",
            email = "max@muster.de"
        )
        customerList.add(customer)
        adapter.notifyItemInserted(customerList.size - 1)
    }
}