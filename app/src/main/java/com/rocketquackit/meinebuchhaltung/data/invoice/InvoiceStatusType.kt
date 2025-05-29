package com.rocketquackit.meinebuchhaltung.data.invoice

import com.rocketquackit.meinebuchhaltung.R


enum class InvoiceStatusType(val displayName: String) {
    OPEN(R.string.salesInvoice_status_open.toString()),
    PAID(R.string.salesInvoice_status_paid.toString()),
    OVERDUE(R.string.salesInvoice_status_overdue.toString()),
    CANCELED(R.string.salesInvoice_status_canceled.toString()),
    DRAFT(R.string.salesInvoice_status_draft.toString()),
}