package com.rocketquackit.meinebuchhaltung.ui.customer.wizard.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.DatabaseProvider
import com.rocketquackit.meinebuchhaltung.data.customer.Customer
import kotlinx.coroutines.launch

class Step1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_customer_create_step1, container, false)

        val inputName = view.findViewById<EditText>(R.id.input_name)
        val inputCompany = view.findViewById<EditText>(R.id.input_company)
        val inputEmail = view.findViewById<EditText>(R.id.input_email)
        val buttonSave = view.findViewById<Button>(R.id.saveButton)

        buttonSave.setOnClickListener {
            val name = inputName.text.toString()
            val company = inputCompany.text.toString()
            val email = inputEmail.text.toString()

            if (name.isBlank()) {
                Toast.makeText(requireContext(), "Name darf nicht leer sein", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = requireContext().getSharedPreferences("firma_prefs", 0)
            val companyName = prefs.getString("aktiveFirma", null)
            if (companyName == null) {
                Toast.makeText(requireContext(), "Keine aktive Firma", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = DatabaseProvider.getCompanyDb(requireContext(), companyName)
            val newCustomer = Customer(
                name = name,
                companyName = company,
                email = email
            )

            lifecycleScope.launch {
                db.customerDao().insert(newCustomer)
                requireActivity().finish() // Schließe Wizard nach Speicherung
            }
        }

        return view
    }

}
