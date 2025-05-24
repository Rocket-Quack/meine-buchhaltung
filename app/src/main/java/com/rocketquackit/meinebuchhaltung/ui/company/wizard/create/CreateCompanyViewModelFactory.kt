package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rocketquackit.meinebuchhaltung.data.company.CompanyDao

class CreateCompanyViewModelFactory(private val companyDao: CompanyDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateCompanyViewModel::class.java)) {
            return CreateCompanyViewModel(companyDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}