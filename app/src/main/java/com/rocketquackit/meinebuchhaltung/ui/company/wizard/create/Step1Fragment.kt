package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.company.CompanyType

class Step1Fragment : Fragment(R.layout.fragment_company_create_step1) {
    // ViewModel wird aus der Activity geholt
    private val viewModel: CreateCompanyViewModel by activityViewModels()
    private lateinit var spinnerCompanyType: Spinner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Eingabefelder und Button finden
        val inputName = view.findViewById<EditText>(R.id.inputCompanyName)

        // Spinner referenzieren
        spinnerCompanyType = view.findViewById(R.id.spinnerCompanyType)

        val nextButton = view.findViewById<Button>(R.id.nextButton)

        // Spinner füllen und initalisieren
        // Enum-Werte holen und in Liste stecken und Adapter erstellen
        val companyTypes = CompanyType.entries.toList()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, companyTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCompanyType.adapter = adapter

        // Vorbefüllen, falls zurückgekehrt wurde
        inputName.setText(viewModel.companyName.value ?: "")

        // Weiter-Button klickbar machen
        nextButton.setOnClickListener {
            // Unternehmensnamen ins ViewModel speichern
            viewModel.companyName.value = inputName.text.toString()

            // Aktuell ausgewählten Enum-Wert aus dem Spinner für Unternehmensform holen
            val selectedPosition = spinnerCompanyType.selectedItemPosition
            val selectedType = companyTypes[selectedPosition]
            // Ins ViewModel speichern
            viewModel.businessType.value = selectedType

            // Zum nächsten Fragment navigieren
            findNavController().navigate(R.id.action_step1_to_step2)
        }
    }
}

