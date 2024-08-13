package com.example.pethub.data.di

import android.content.Context
import com.example.pethub.data.local.PetDatabase
import com.example.pethub.data.local.UserDatabase
import com.example.pethub.data.repository.PetRepository

object Injection {
    fun provideRepository(context: Context): PetRepository {
        val petDatabase = PetDatabase.getInstance(context)
        val petDao = petDatabase.petDao()
        val userDatabase = UserDatabase.getInstance(context)
        val userDao = userDatabase.userDao()

        return PetRepository.getInstance(petDao, userDao)
    }
}