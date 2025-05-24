package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.company.CompanyDatabase

class Step1Fragment : Fragment(R.layout.fragment_company_create_step1) {
    // ViewModel wird aus der Activity geholt
    private val viewModel: CreateCompanyViewModel by activityViewModels {
        val db = CompanyDatabase.getDatabase(requireContext(), "firmen.db")
        CreateCompanyViewModelFactory(db.firmaDao())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Eingabefelder und Button finden
        val inputName = view.findViewById<EditText>(R.id.inputCompanyName)
        val inputBusinessType = view.findViewById<EditText>(R.id.inputBusinessType)
        val nextButton = view.findViewById<Button>(R.id.nextButton)

        // Vorbefüllen, falls zurückgekehrt wurde
        inputName.setText(viewModel.companyName.value ?: "")
        inputBusinessType.setText(viewModel.businessType.value ?: "")

        // Weiter-Button klickbar machen
        nextButton.setOnClickListener {
            // Daten ins ViewModel speichern
            viewModel.companyName.value = inputName.text.toString()
            viewModel.businessType.value = inputBusinessType.text.toString()

            // Zum nächsten Fragment navigieren
            findNavController().navigate(R.id.action_step1_to_step2)
        }
    }
}

