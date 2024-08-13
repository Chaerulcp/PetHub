package com.example.pethub.ui.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pethub.data.repository.PetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val repository: PetRepository
) : ViewModel() {

    private val _isLogin: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isLogin: MutableStateFlow<Boolean?>
        get() = _isLogin

    init {
        isUserLoggedIn()
    }

    fun isUserLoggedIn() {
        viewModelScope.launch {
            repository.isLogin()
                .collect {
                    _isLogin.value = it
                }
        }
    }
}