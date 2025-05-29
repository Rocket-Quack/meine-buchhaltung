package com.rocketquackit.meinebuchhaltung.data.audit_trail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audit_trail")
data class AuditTrail(
    @PrimaryKey(autoGenerate = true)
    val auditId: Int = 0,

    val date: Long = System.currentTimeMillis(),   // Zeitstempel der Änderung
    val action: String,                            // Art der Aktion (z.B. CREATE, UPDATE, DELETE, STORNO)
    val entityType: String,                        // Typ der Entität (z.B. "LedgerEntry", "Invoice")
    val entityId: Int,                             // ID der betroffenen Entität

    val oldValue: String? = null,                  // Optional: alter Wert (z.B. JSON oder Text)
    val newValue: String? = null                   // Optional: neuer Wert (z.B. JSON oder Text)
)
