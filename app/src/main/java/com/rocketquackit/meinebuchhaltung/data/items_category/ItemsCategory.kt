package com.rocketquackit.meinebuchhaltung.data.items_category

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "items_category",
    indices = [
        Index(value = ["name"], unique = true)
    ]
)
data class ItemsCategory(

        @PrimaryKey(autoGenerate = true)
        val categoryId: Int = 0,
        val name: String
)
