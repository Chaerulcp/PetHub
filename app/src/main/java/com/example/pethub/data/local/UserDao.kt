package com.example.pethub.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pethub.data.model.PetEntity
import com.example.pethub.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): Flow<UserEntity?>

    @Query("SELECT EXISTS(SELECT * FROM user)")
    fun isLogin(): Flow<Boolean>

    @Query("DELETE FROM user")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserEntity)
}