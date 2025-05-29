package com.rocketquackit.meinebuchhaltung.data.audit_trail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AuditTrailDao {

    @Insert
    suspend fun insertAuditEntry(auditEntry: AuditTrail)

    @Query("SELECT * FROM audit_trail WHERE entityType = :entityType AND entityId = :entityId")
    suspend fun getAuditTrailForEntity(entityType: String, entityId: Int): List<AuditTrail>

}