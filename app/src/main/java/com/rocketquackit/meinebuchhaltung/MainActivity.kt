// Copyright (c) 2025 RocketQuackIT
// Alle Rechte vorbehalten. Keine Verwendung ohne schriftliche Genehmigung.

package com.rocketquackit.meinebuchhaltung

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import com.rocketquackit.meinebuchhaltung.databinding.ActivityMainBinding
import com.rocketquackit.meinebuchhaltung.ui.company.CompanySelectActivity

class MainActivity : AppCompatActivity() {

    // Objekt zur Verwaltung der Top-Level-Ziele in der Navigation (z. B. Home, Kunden)
    private lateinit var appBarConfiguration: AppBarConfiguration

    // ViewBinding-Objekt für die Hauptansicht (activity_main.xml)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Layout über ViewBinding aufbauen
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Die Toolbar (App-Leiste oben) als ActionBar setzen
        setSupportActionBar(binding.appBarMain.toolbar)

        // Zugriff auf die Navigation Drawer-Komponenten (Seitennavigation)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val headerView = navView.getHeaderView(0)
        val userNameTextView = headerView.findViewById<TextView>(R.id.userNameTextView)
        val userEmailTextView = headerView.findViewById<TextView>(R.id.userEmailTextView)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            if (user.isAnonymous) {
                userNameTextView.text = "Anonym"
                userEmailTextView.text = "" // Keine E-Mail für anonym
            } else {
                userNameTextView.text = user.displayName ?: "Angemeldet"
                userEmailTextView.text = user.email ?: ""
            }
        } else {
            userNameTextView.text = "Nicht angemeldet"
            userEmailTextView.text = ""
        }



        // Zugriff auf den NavController, der Navigation zwischen Fragmenten steuert
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Definition, welche Menüeinträge als „Top-Level“ gelten (keine Zurück-Navigation nötig)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_customers
            ),
            drawerLayout // Drawer wird mit Navigation verbunden
        )

        // ActionBar mit dem NavController verbinden (zeigt Titel, Pfeil, Menü-Symbol usw.)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Die Navigation im Drawer mit dem NavController verbinden
        navView.setupWithNavController(navController)

        // Manuelle Navigation für benutzerdefinierte Menüeinträge
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                // Benutzer klickt im Menü auf „Firma wechseln“
                R.id.nav_firma_wechseln -> {
                    // Aktive Firma aus dem Speicher entfernen
                    val prefs = getSharedPreferences("firma_prefs", MODE_PRIVATE)
                    prefs.edit().remove("aktiveFirma").apply()

                    // Zur Firmenauswahl springen, alte Activities werden geschlossen
                    val intent = Intent(this, CompanySelectActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    true // Event wurde verarbeitet
                }

                // Alle anderen Menüeinträge wie gewohnt behandeln
                else -> {
                    val handled = NavigationUI.onNavDestinationSelected(menuItem, navController)
                    if (handled) {
                        drawerLayout.closeDrawers() // Navigation Drawer schließen
                    }
                    handled // Rückgabe: wurde Menü-Eintrag verarbeitet?
                }
            }
        }
    }

    // Menü in der App-Leiste (drei Punkte rechts oben) erzeugen
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Menü-Ressource (res/menu/main.xml) einbinden
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    // Wenn oben auf den „Zurück“-Pfeil gedrückt wird, wird dies ausgeführt
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
