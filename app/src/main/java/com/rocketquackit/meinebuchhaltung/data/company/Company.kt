package com.rocketquackit.meinebuchhaltung.data.company

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "companys")
data class Company(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val createdAt: Long = System.currentTimeMillis()
)