package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.materialswitch.MaterialSwitch
import com.rocketquackit.meinebuchhaltung.R

class Step2Fragment : Fragment(R.layout.fragment_company_create_step2) {
    // ViewModel wird aus der Activity geholt
    private val viewModel: CreateCompanyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputTaxNumber = view.findViewById<EditText>(R.id.inputTaxNumber)
        val inputVatNumber = view.findViewById<EditText>(R.id.inputVatNumber)
        val nextButton = view.findViewById<Button>(R.id.nextButton)
        val switchHasVatId = view.findViewById<MaterialSwitch>(R.id.switchHasVatId)

        // Vorbefüllen der Felder
        inputTaxNumber.setText(viewModel.taxNumber.value ?: "")
        inputVatNumber.setText(viewModel.vatNumber.value ?: "")

        // Fortschritts Balken anzeigen und Fortschritt zeigen
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.setProgress(40, true) // Fortschritt in Prozent (0-100)

        // Sobald alle Felder aktiviert wird der Alpha Wert des Buttons auf 1 gesetzt und aktiviert
        fun updateButtonState() {
            val taxFilled = inputTaxNumber.text.toString().isNotBlank()
            val vatRequired = switchHasVatId.isChecked
            val vatFilled = inputVatNumber.text.toString().isNotBlank()

            val isValid = taxFilled && (!vatRequired || vatFilled)

            nextButton.isEnabled = isValid
            nextButton.alpha = if (isValid) 1.0f else 0.5f
        }

        // Listener registrieren
        inputTaxNumber.addTextChangedListener { updateButtonState() }
        inputVatNumber.addTextChangedListener { updateButtonState() }
        switchHasVatId.setOnCheckedChangeListener { _, isChecked ->
            inputVatNumber.visibility = if (isChecked) View.VISIBLE else View.GONE
            updateButtonState()
        }

        // initial prüfen
        updateButtonState()

        nextButton.setOnClickListener {

            // Steuernummer aus dem Eingabe Feld holen und dabei Trailing-Whitespace entfernen
            val taxNumber = inputTaxNumber.text.toString().trimEnd()
            // Entferne Leerzeichen und evtl. Trennzeichen
            val cleanedTaxNumber = taxNumber.replace(Regex("[^\\d]"), "")
            // Validierung: Prüfen, ob das Feld leer ist
            if (taxNumber.isEmpty()) {
                inputTaxNumber.error = "@string/textViewCompanyWizardMissingTaxNumber"
                Toast.makeText(requireContext(), "@string/textViewCompanyWizardMissingTaxNumber", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Validierung: Prüfen, ob die Steuernummer im  gültigen Format ist
            if (!cleanedTaxNumber.matches(Regex("^\\d{10,13}$"))) {
                Toast.makeText(requireContext(), "@string/textViewCompanyWizardWrongTaxNumber", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            viewModel.taxNumber.value = inputTaxNumber.text.toString()
            viewModel.vatNumber.value = inputVatNumber.text.toString()
            findNavController().navigate(R.id.action_step2_to_step3)
        }
    }
}
