package com.example.pethub.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pethub.data.model.UserEntity
import com.example.pethub.data.repository.PetRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: PetRepository
) : ViewModel() {

    fun register(user: UserEntity) {
        viewModelScope.launch {
            repository.register(user)
        }
    }
}