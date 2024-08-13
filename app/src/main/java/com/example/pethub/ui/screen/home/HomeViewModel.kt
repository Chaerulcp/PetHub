package com.example.pethub.ui.screen.home

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.example.pethub.data.model.Pet
import com.example.pethub.data.model.UserEntity
import com.example.pethub.data.repository.PetRepository
import kotlinx.coroutines.launch
import com.example.pethub.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch

class HomeViewModel(
    private val repository: PetRepository
) : ViewModel() {
    private val _petsData = mutableStateOf<UiState<List<Pet>>>(UiState.Loading)
    val petsData: State<UiState<List<Pet>>>
        get() = _petsData

    private val _user: MutableStateFlow<UserEntity?> = MutableStateFlow(UserEntity(name = "Person"))
    val user: MutableStateFlow<UserEntity?>
        get() = _user

    init {
        getUser()
    }

    fun getPets() {
        viewModelScope.launch {
            repository.getAllPets().observeForever {
                when (it) {
                    is UiState.Loading -> _petsData.value = UiState.Loading
                    is UiState.Success -> _petsData.value = UiState.Success(it.data)
                    is UiState.Error -> _petsData.value = UiState.Error(it.errorMessage)
                }
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            repository.getUser()
                .collect { isListed ->
                    _user.value = isListed
                }
        }
    }
}