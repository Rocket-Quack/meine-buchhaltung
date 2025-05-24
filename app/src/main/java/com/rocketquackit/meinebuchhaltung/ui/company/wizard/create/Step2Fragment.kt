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

class Step2Fragment : Fragment(R.layout.fragment_company_create_step2) {
    private val viewModel: CreateCompanyViewModel by activityViewModels {
        val db = CompanyDatabase.getDatabase(requireContext(), "firmen.db")
        CreateCompanyViewModelFactory(db.firmaDao())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputTaxNumber = view.findViewById<EditText>(R.id.inputTaxNumber)
        val inputVatNumber = view.findViewById<EditText>(R.id.inputVatNumber)
        val nextButton = view.findViewById<Button>(R.id.nextButton)

        // Vorbefüllen der Felder
        inputTaxNumber.setText(viewModel.taxNumber.value ?: "")
        inputVatNumber.setText(viewModel.vatNumber.value ?: "")

        nextButton.setOnClickListener {
            viewModel.taxNumber.value = inputTaxNumber.text.toString()
            viewModel.vatNumber.value = inputVatNumber.text.toString()
            findNavController().navigate(R.id.action_step2_to_step3)
        }
    }
}
