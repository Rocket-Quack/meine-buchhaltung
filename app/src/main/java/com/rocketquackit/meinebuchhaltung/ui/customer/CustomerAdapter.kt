package com.rocketquackit.meinebuchhaltung.ui.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.customer.Customer

/**
 * Adapter für die Kundenliste, optimiert mit DiffUtil.
 * Verwendet ListAdapter, um effizient nur geänderte Einträge zu aktualisieren.
 * Nur das, was sich wirklich in der Liste ändert, wird angezeigt
 */
class CustomerAdapter : ListAdapter<Customer, CustomerAdapter.CustomerViewHolder>(CustomerDiffCallback()) {

    class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.text_name)
        val companyText: TextView = itemView.findViewById(R.id.text_company)
        val streetText: TextView = itemView.findViewById(R.id.text_street)
        val cityText: TextView = itemView.findViewById(R.id.text_city)
        val emailText: TextView = itemView.findViewById(R.id.text_email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_customer, parent, false)
        return CustomerViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = getItem(position)
        holder.nameText.text = customer.name
        holder.companyText.text = customer.companyName
        holder.streetText.text = "${customer.street} ${customer.houseNumber}"
        holder.cityText.text = "${customer.zipCode} ${customer.city}"
        holder.emailText.text = customer.email
    }
}

/**
 * Vergleichsfunktion für Kundenobjekte.
 * Vergleicht sowohl die ID (für Identität) als auch den Inhalt.
 */
class CustomerDiffCallback : DiffUtil.ItemCallback<Customer>() {
    override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
        return oldItem == newItem
    }
}
