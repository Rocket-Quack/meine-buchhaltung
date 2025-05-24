package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.ui.company.CompanySelectActivity

class Step5Fragment : Fragment(R.layout.fragment_company_create_step5) {
    // ViewModel wird aus der Activity geholt
    private val viewModel: CreateCompanyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val inputIban = view.findViewById<EditText>(R.id.inputIBAN)
        val inputBic = view.findViewById<EditText>(R.id.inputBIC)
        val inputBankName = view.findViewById<EditText>(R.id.inputBankName)
        val finishButton = view.findViewById<Button>(R.id.finishButton)

        // Vorbefüllen der Felder, falls der Nutzer zurücknavigiert ist
        inputIban.setText(viewModel.companyBankIBAN.value ?: "")
        inputBic.setText(viewModel.companyBankBIC.value ?: "")
        inputBankName.setText(viewModel.companyBankName.value ?: "")

        finishButton.setOnClickListener {
            // Speichern der Daten ins ViewModel
            viewModel.companyBankIBAN.value = inputIban.text.toString()
            viewModel.companyBankBIC.value = inputBic.text.toString()
            viewModel.companyBankName.value = inputBankName.text.toString()

            // Speichern in die Room-Datenbank
            viewModel.saveCompany()

            // Zur Übersichtsseite zurückkehren (CompanySelectActivity)
            val intent = Intent(requireContext(), CompanySelectActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            activity?.finish()
        }
    }
}
