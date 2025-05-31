package com.rocketquackit.meinebuchhaltung.ui.company

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.rocketquackit.meinebuchhaltung.MainActivity
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.DatabaseProvider
import com.rocketquackit.meinebuchhaltung.data.company.Company
import com.rocketquackit.meinebuchhaltung.data.global.AllCompaniesDatabase
import com.rocketquackit.meinebuchhaltung.ui.auth.LoginActivity
import kotlinx.coroutines.launch

class CompanySelectActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var buttonErstellen: Button
    private lateinit var buttonLaden: Button

    private var selectedCompany: Company? = null
    private lateinit var db: AllCompaniesDatabase
    private lateinit var adapter: ArrayAdapter<String>
    private val firmenListe = mutableListOf<Company>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Prüfen, ob der Benutzer eingeloggt ist
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            // Nicht eingeloggt, zur LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // CompanySelectActivity beenden
            return
        }

        setContentView(R.layout.activity_firma_auswahl)

        listView = findViewById(R.id.firmenListView)
        buttonErstellen = findViewById(R.id.buttonErstellen)
        buttonLaden = findViewById(R.id.buttonLaden)

        db = DatabaseProvider.getCompaniesDb(applicationContext)

        // Firmen laden
        loadFirmen()

        // Neue Firma anlegen
        buttonErstellen.setOnClickListener {
            val uid = user.uid
            // Startet den Wizard (CreateCompanyActivity)
            val intent = Intent(this, com.rocketquackit.meinebuchhaltung.ui.company.wizard.create.CreateCompanyActivity::class.java)
            intent.putExtra("USER_ID", uid)
            startActivity(intent)
        }

        // Firma auswählen
        listView.setOnItemClickListener { _, _, position, _ ->
            selectedCompany = firmenListe[position]
            Toast.makeText(this, "Ausgewählt: ${selectedCompany?.companyName}", Toast.LENGTH_SHORT).show()
        }

        // Firma laden
        buttonLaden.setOnClickListener {
            selectedCompany?.let { firma ->

                // Ausgewählter Firmenname im Speicher ablegen
                val prefs = getSharedPreferences("firma_prefs", MODE_PRIVATE)
                prefs.edit().putString("aktiveFirma", firma.companyName).apply()

                // MainActivity starten
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("firmaName", firma.companyName)
                startActivity(intent)
                finish()
            } ?: run {
                Toast.makeText(this, "Bitte wähle eine Firma aus", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadFirmen() {
        lifecycleScope.launch {
            val firmen = db.companyDao().getAll()
            firmenListe.clear()
            firmenListe.addAll(firmen)
            val namen = firmenListe.map { it.companyName }
            adapter = ArrayAdapter(this@CompanySelectActivity, android.R.layout.simple_list_item_1, namen)
            listView.adapter = adapter
        }
    }
}
