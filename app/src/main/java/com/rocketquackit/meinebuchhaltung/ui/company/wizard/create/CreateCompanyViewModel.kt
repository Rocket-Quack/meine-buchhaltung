package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rocketquackit.meinebuchhaltung.data.company.Company
import com.rocketquackit.meinebuchhaltung.data.company.CompanyDao
import com.rocketquackit.meinebuchhaltung.data.company.CompanyType
import kotlinx.coroutines.launch

class CreateCompanyViewModel(private val companyDao: CompanyDao) : ViewModel() {

    // Felder für die Eingabe, passend zur Company-Datenklasse
    val companyName = MutableLiveData<String>()
    val businessType = MutableLiveData<CompanyType?>()
    val taxNumber = MutableLiveData<String>()
    val vatNumber = MutableLiveData<String?>()
    val registrationNumber = MutableLiveData<String?>()
    val address = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String?>()
    val email = MutableLiveData<String>()
    val website = MutableLiveData<String?>()
    val companyBankIBAN = MutableLiveData<String?>()
    val companyBankBIC = MutableLiveData<String?>()
    val companyBankName = MutableLiveData<String?>()
    val companyLogo = MutableLiveData<ByteArray?>()

    // Speichert die eingegebene Firma in der Datenbank
    fun saveCompany() {
        viewModelScope.launch {
            val company = Company(
                companyName = companyName.value ?: "",
                businessType = businessType.value ?: throw IllegalStateException("Unternehmensform muss ausgewählt werden"),
                taxNumber = taxNumber.value ?: "",
                vatNumber = vatNumber.value,
                registrationNumber = registrationNumber.value,
                address = address.value ?: "",
                phoneNumber = phoneNumber.value,
                email = email.value ?: "",
                website = website.value,
                companyBankIBAN = companyBankIBAN.value,
                companyBankBIC = companyBankBIC.value,
                companyBankName = companyBankName.value,
                companyLogo = companyLogo.value
                // createdAt und lastModifiedAt werden automatisch gesetzt
            )
            companyDao.insert(company)
        }
    }
}
