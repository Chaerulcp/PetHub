package com.example.pethub.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.pethub.data.dummy.getDummyPetData
import com.example.pethub.data.local.PetDao
import com.example.pethub.data.local.UserDao
import com.example.pethub.data.model.Pet
import com.example.pethub.data.model.PetEntity
import com.example.pethub.data.model.UserEntity
import com.example.pethub.ui.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class PetRepository private constructor(
    private val petDao: PetDao,
    private val userDao: UserDao
) {

    suspend fun register(user: UserEntity) = userDao.insert(user)

    suspend fun logout() = userDao.delete()

    fun getUser(): Flow<UserEntity?> = userDao.getUser()

    fun isLogin(): Flow<Boolean> = userDao.isLogin()

    fun getAllPets(): LiveData<UiState<List<Pet>>> = liveData(Dispatchers.IO) {
        emit(UiState.Loading)
        try {
            val pets = getDummyPetData()
            emit(UiState.Success(pets))
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
        }
    }

    fun getPetById(id: String): LiveData<UiState<Pet>> = liveData(Dispatchers.IO) {
        emit(UiState.Loading)
        try {
            val pets = getDummyPetData()
            val pet = pets.find { it.id == id }
            if (pet != null) {
                emit(UiState.Success(pet))
            } else {
                emit(UiState.Error("Not Found"))
            }
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
        }
    }

    fun getListedPets(): Flow<List<PetEntity>> = petDao.getListing()

    fun isListed(id: String): Flow<Boolean> = petDao.isListed(id)

    suspend fun delete(id: String) = petDao.delete(id)

    suspend fun insert(pet: PetEntity) = petDao.insert(pet)

    companion object {
        @Volatile
        private var instance: PetRepository? = null
        fun getInstance(
            petDao: PetDao,
            userDao: UserDao
        ): PetRepository =
            instance ?: synchronized(this) {
                instance ?: PetRepository(petDao, userDao)
            }.also { instance = it }
    }
}