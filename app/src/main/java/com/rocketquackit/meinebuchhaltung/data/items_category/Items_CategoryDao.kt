package com.rocketquackit.meinebuchhaltung.data.items_category

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * DAO = Data Access Object
 * Diese Schnittstelle erlaubt den Zugriff auf die items_category-Tabelle.
 */
@Dao
interface Items_CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itemsCategory: ItemsCategory)

    @Query("SELECT * FROM items_category")
    suspend fun getAllCategories(): List<ItemsCategory>

    @Delete
    suspend fun delete(itemsCategory: ItemsCategory)

}