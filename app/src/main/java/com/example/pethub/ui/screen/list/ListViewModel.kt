package com.example.pethub.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pethub.data.model.PetEntity
import com.example.pethub.data.repository.PetRepository
import com.example.pethub.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: PetRepository
) : ViewModel() {

    private val _listedPet: MutableStateFlow<UiState<List<PetEntity>>> = MutableStateFlow(UiState.Loading)
    val listedPet: MutableStateFlow<UiState<List<PetEntity>>>
        get() = _listedPet

    fun getListedPet() {
        viewModelScope.launch {
            repository.getListedPets()
                .catch {
                    _listedPet.value = UiState.Error(it.message.toString())
                }
                .collect { pet ->
                    _listedPet.value = UiState.Success(pet)
                }
        }
    }
}