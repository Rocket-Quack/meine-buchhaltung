package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.global.Address

class Step3Fragment : Fragment(R.layout.fragment_company_create_step3) {
    // ViewModel wird aus der Activity geholt
    private val viewModel: CreateCompanyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val inputStreet = view.findViewById<EditText>(R.id.inputStreet)
        val inputHouseNumber = view.findViewById<EditText>(R.id.inputHouseNumber)
        val inputPostalCode = view.findViewById<EditText>(R.id.inputPostalCode)
        val inputCity = view.findViewById<EditText>(R.id.inputCity)
        val inputCountry = view.findViewById<AutoCompleteTextView>(R.id.inputCountry)
        val inputPhone = view.findViewById<EditText>(R.id.inputPhone)
        val nextButton = view.findViewById<Button>(R.id.nextButton)

        inputPhone.setText(viewModel.phoneNumber.value ?: "")

        // Länderliste für Dropdown (später eigene Room-Tabelle nutzen)
        val countries = listOf("Deutschland (DE)") // TODO: Bei Bedarf als Room-Tabelle auslagern
        val countryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, countries)
        inputCountry.setAdapter(countryAdapter)
        inputCountry.setText("Deutschland (DE)", false)

        // Fortschritts Balken anzeigen und Fortschritt zeigen
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.setProgress(60, true) // Fortschritt in Prozent (0-100)

        nextButton.setOnClickListener {
            val street = inputStreet.text.toString().trim()
            val houseNumber = inputHouseNumber.text.toString().trim()
            val postalCode = inputPostalCode.text.toString().trim()
            val city = inputCity.text.toString().trim()
            val country = inputCountry.text.toString().trim()
            val phone = inputPhone.text.toString().trim()

            // Werte ins ViewModel speichern
            viewModel.address.value = Address(
                street = street,
                houseNumber = houseNumber,
                postalCode = postalCode,
                city = city,
                country = country
            )

            viewModel.phoneNumber.value = phone

            // Weiter zur nächsten Seite
            findNavController().navigate(R.id.action_step3_to_step4)
        }
    }
}
