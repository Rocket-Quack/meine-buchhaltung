package com.rocketquackit.meinebuchhaltung.data.company

import androidx.room.*

@Dao
interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(company: Company)

    @Delete
    suspend fun delete(company: Company)

    @Query("SELECT * FROM companys")
    suspend fun getAll(): List<Company>
}