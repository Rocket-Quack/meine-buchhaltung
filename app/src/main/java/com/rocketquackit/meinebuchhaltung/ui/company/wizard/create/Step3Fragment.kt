package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rocketquackit.meinebuchhaltung.R

class Step3Fragment : Fragment(R.layout.fragment_company_create_step3) {
    // ViewModel wird aus der Activity geholt
    private val viewModel: CreateCompanyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val inputAddress = view.findViewById<EditText>(R.id.inputAddress)
        val inputPhone = view.findViewById<EditText>(R.id.inputPhone)
        val nextButton = view.findViewById<Button>(R.id.nextButton)

        inputAddress.setText(viewModel.address.value ?: "")
        inputPhone.setText(viewModel.phoneNumber.value ?: "")

        nextButton.setOnClickListener {
            viewModel.address.value = inputAddress.text.toString()
            viewModel.phoneNumber.value = inputPhone.text.toString()
            findNavController().navigate(R.id.action_step3_to_step4)
        }
    }
}
