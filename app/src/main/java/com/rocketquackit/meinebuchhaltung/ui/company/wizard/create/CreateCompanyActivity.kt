package com.rocketquackit.meinebuchhaltung.ui.company.wizard.create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.company.CompanyDatabase

class CreateCompanyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_company)

        val db = CompanyDatabase.getDatabase(applicationContext, "firmen.db")
        val factory = CreateCompanyViewModelFactory(db.firmaDao())
        ViewModelProvider(this, factory).get(CreateCompanyViewModel::class.java)
    }
}

