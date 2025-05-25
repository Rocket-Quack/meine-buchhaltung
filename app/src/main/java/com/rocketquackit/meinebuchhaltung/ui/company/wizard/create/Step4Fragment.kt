package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rocketquackit.meinebuchhaltung.R

class Step4Fragment : Fragment(R.layout.fragment_company_create_step4) {
    // ViewModel wird aus der Activity geholt
    private val viewModel: CreateCompanyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val inputEmail = view.findViewById<EditText>(R.id.inputEmail)
        val inputWebsite = view.findViewById<EditText>(R.id.inputWebsite)
        val nextButton = view.findViewById<Button>(R.id.nextButton)

        inputEmail.setText(viewModel.email.value ?: "")
        inputWebsite.setText(viewModel.website.value ?: "")

        nextButton.setOnClickListener {
            viewModel.email.value = inputEmail.text.toString()
            viewModel.website.value = inputWebsite.text.toString()
            findNavController().navigate(R.id.action_step4_to_step5)
        }
    }
}
