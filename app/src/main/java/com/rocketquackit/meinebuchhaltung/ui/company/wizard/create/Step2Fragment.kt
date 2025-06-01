package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.rocketquackit.meinebuchhaltung.R

class Step2Fragment : Fragment(R.layout.fragment_company_create_step2) {
    // ViewModel wird aus der Activity geholt
    private val viewModel: CreateCompanyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputTaxNumber = view.findViewById<EditText>(R.id.inputTaxNumber)
        val inputVatNumber = view.findViewById<EditText>(R.id.inputVatNumber)
        val nextButton = view.findViewById<Button>(R.id.nextButton)

        // Vorbefüllen der Felder
        inputTaxNumber.setText(viewModel.taxNumber.value ?: "")
        inputVatNumber.setText(viewModel.vatNumber.value ?: "")

        // Fortschritts Balken anzeigen und Fortschritt zeigen
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.setProgress(40, true) // Fortschritt in Prozent (0-100)


        nextButton.setOnClickListener {
            viewModel.taxNumber.value = inputTaxNumber.text.toString()
            viewModel.vatNumber.value = inputVatNumber.text.toString()
            findNavController().navigate(R.id.action_step2_to_step3)
        }
    }
}
