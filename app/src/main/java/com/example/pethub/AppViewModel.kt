package com.example.pethub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pethub.data.repository.PetRepository
import kotlinx.coroutines.launch

class AppViewModel (
    private val repository: PetRepository
) : ViewModel() {


    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}