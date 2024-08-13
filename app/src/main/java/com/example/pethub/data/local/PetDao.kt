package com.example.pethub.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pethub.data.model.PetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Query("SELECT * FROM pet")
    fun getListing(): Flow<List<PetEntity>>

    @Query("SELECT EXISTS(SELECT * FROM pet WHERE id = :id)")
    fun isListed(id: String): Flow<Boolean>

    @Query("DELETE FROM pet WHERE id = :id")
    suspend fun delete(id: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pet: PetEntity)
}