package com.rocketquackit.meinebuchhaltung.ui.customer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rocketquackit.meinebuchhaltung.data.DatabaseProvider
import com.rocketquackit.meinebuchhaltung.data.customer.Customer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CustomerViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences("firma_prefs", 0)
    private val companyName = prefs.getString("aktiveFirma", null)
        ?: throw IllegalStateException("Keine aktive Firma gefunden")

    private val db = DatabaseProvider.getCompanyDb(application, companyName)

    val customers: StateFlow<List<Customer>> = db.customerDao()
        .getAllCustomersSortedFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
}
