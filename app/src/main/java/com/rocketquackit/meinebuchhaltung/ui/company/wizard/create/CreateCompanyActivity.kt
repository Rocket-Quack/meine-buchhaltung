package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.company.CompanyDatabase

class CreateCompanyActivity : AppCompatActivity() {

    private val viewModel by viewModels<CreateCompanyViewModel> {
        val db = CompanyDatabase.getDatabase(applicationContext, "firmen.db")
        CreateCompanyViewModelFactory(db.firmaDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_company)
    }
}
