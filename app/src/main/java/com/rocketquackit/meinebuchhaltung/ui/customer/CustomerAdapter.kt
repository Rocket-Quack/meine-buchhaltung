package com.rocketquackit.meinebuchhaltung.ui.customer

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScroller
import com.rocketquackit.meinebuchhaltung.R
import com.rocketquackit.meinebuchhaltung.data.customer.Customer

/**
 * Adapter für die Kundenliste, optimiert mit DiffUtil.
 * Verwendet ListAdapter, um effizient nur geänderte Einträge zu aktualisieren.
 * Nur das, was sich wirklich in der Liste ändert, wird angezeigt
 */
class CustomerAdapter(
    private val onCustomerClick: (Customer) -> Unit
) : ListAdapter<Customer, CustomerAdapter.CustomerViewHolder>(CustomerDiffCallback()), FastScroller.SectionIndexer {


    class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.text_name)
        val companyText: TextView = itemView.findViewById(R.id.text_company)
        val logoImage: ImageView = itemView.findViewById(R.id.image_logo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_customer, parent, false)
        return CustomerViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = getItem(position)
        holder.nameText.text = customer.name

        // Wenn ein Firmenname vorhanden ist, zeige es an, sonst zeige es nicht
        if (customer.companyName.isNullOrEmpty()) {
            holder.companyText.visibility = View.GONE
        } else {
            holder.companyText.text = customer.companyName
            holder.companyText.visibility = View.VISIBLE
        }

        // Wenn ein Logo vorhanden ist, zeige es an, sonst zeige ein Platzhalterbild
        if (customer.companyLogo != null) {
            val bitmap = BitmapFactory.decodeByteArray(customer.companyLogo, 0, customer.companyLogo.size)
            holder.logoImage.setImageBitmap(bitmap)
        } else {
            holder.logoImage.setImageResource(R.drawable.company)
        }

        // Setze den Klick-Listener
        holder.itemView.setOnClickListener {
            onCustomerClick(customer)
        }
    }

    override fun getSectionText(position: Int): String {
        return getItem(position).name.firstOrNull()?.uppercase() ?: "#"
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
