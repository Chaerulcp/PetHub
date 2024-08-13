package com.example.pethub.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pethub.data.model.Pet
import com.example.pethub.data.model.PetEntity
import com.example.pethub.data.repository.PetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.example.pethub.ui.common.UiState

class DetailViewModel(
    private val repository: PetRepository
) : ViewModel() {
    private val _petData = mutableStateOf<UiState<Pet>>(UiState.Loading)
    val petData: State<UiState<Pet>>
        get() = _petData

    private val _isListed: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isListed: MutableStateFlow<Boolean>
        get() = _isListed

    fun getPetDetail(id: String) {
        viewModelScope.launch {
            repository.getPetById(id).observeForever {
                when (it) {
                    is UiState.Loading -> _petData.value = UiState.Loading
                    is UiState.Success -> _petData.value = UiState.Success(it.data)
                    is UiState.Error -> _petData.value = UiState.Error(it.errorMessage)
                }
            }
        }
    }

    fun isPetListed(id: String) {
        viewModelScope.launch {
            repository.isListed(id)
                .catch {
                    _isListed.value = false
                }
                .collect { isListed ->
                    _isListed.value = isListed
                }
        }
    }

    fun toggleListedPet(pet: PetEntity) {
        viewModelScope.launch {
            if (_isListed.value) {
                repository.delete(pet.id)
            } else {
                repository.insert(pet)
            }
        }
    }
}