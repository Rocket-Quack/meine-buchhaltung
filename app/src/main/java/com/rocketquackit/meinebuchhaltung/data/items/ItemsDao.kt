package com.rocketquackit.meinebuchhaltung.data.items

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * DAO = Data Access Object
 * Diese Schnittstelle erlaubt den Zugriff auf die items-Tabelle.
 */
@Dao
interface ItemsDao {

    // Einfügen eines neuen Artikels
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Items): Long

    // Mehrere Artikel auf einmal einfügen
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<Items>): List<Long>

    // Artikel aktualisieren
    @Update
    suspend fun updateItem(item: Items)

    // Artikel löschen
    @Delete
    suspend fun deleteItem(item: Items)

    // Abrufen aller Artikel
    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<Items>

    // Artikel nach ID abrufen
    @Query("SELECT * FROM items WHERE articleId = :id")
    suspend fun getItemById(id: Int): Items?

    // Optional: Suche nach Name
    @Query("SELECT * FROM items WHERE name LIKE '%' || :query || '%'")
    suspend fun searchItemsByName(query: String): List<Items>

    // Optional: Abrufen aller Artikel nach Kategorie
    @Query("SELECT * FROM items WHERE categoryId = :categoryId")
    suspend fun getItemsByCategory(categoryId: Int): List<Items>

}