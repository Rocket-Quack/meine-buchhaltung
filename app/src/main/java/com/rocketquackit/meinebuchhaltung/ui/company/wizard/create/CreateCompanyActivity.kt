package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.DatabaseProvider

class CreateCompanyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_company)

        val db = DatabaseProvider.getCompaniesDb(applicationContext)
        val factory = CreateCompanyViewModelFactory(db.companyDao())
        ViewModelProvider(this, factory).get(CreateCompanyViewModel::class.java)
    }
}

