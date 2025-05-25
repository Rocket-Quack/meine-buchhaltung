package com.rocketquackit.meinebuchhaltung.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rocketquackit.meinebuchhaltung.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // View Binding (bindet das Layout)
        _binding = com.rocketquackit.meinebuchhaltung.databinding.FragmentHomeBinding.inflate(
            inflater, container, false
        )
        val root: View = binding.root

        // Zugriff auf das TextView, das im Layout "textHome" heißt
        val textView: TextView = binding.textHome

        // Aktive Firma aus SharedPreferences lesen
        val prefs = requireContext().getSharedPreferences("firma_prefs", android.content.Context.MODE_PRIVATE)
        val aktiveFirma = prefs.getString("aktiveFirma", "keine")
        val dbName = "firma_${aktiveFirma}.db"

        // Text anzeigen
        textView.text = "Aktive Firma: $aktiveFirma\nDatenbank: $dbName"

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}