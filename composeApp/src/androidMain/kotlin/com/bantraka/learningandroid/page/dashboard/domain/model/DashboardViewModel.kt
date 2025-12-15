package com.bantraka.learningandroid.page.dashboard.domain.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bantraka.learningandroid.database.User
import com.bantraka.learningandroid.page.dashboard.domain.usecase.AddUserUseCase
import com.bantraka.learningandroid.page.dashboard.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DashboardUiState(
    val users: List<User> = emptyList(),
    val name: String = "",
    val age: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class DashboardViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState

    init {
        loadUsers()
    }

    fun onNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(name = newName)
    }

    fun onAgeChange(newAge: String) {
        _uiState.value = _uiState.value.copy(age = newAge)
    }

    fun addUser() {
        val state = _uiState.value

        // Validasi
        if (state.name.isBlank()) {
            _uiState.value = state.copy(errorMessage = "Nama harus diisi")
            return
        }

        val ageInt = state.age.toIntOrNull()
        if (ageInt == null) {
            _uiState.value = state.copy(errorMessage = "Umur harus angka")
            return
        }

        _uiState.value = state.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            addUserUseCase(state.name, ageInt)
            val users = getUsersUseCase()
            _uiState.value = state.copy(
                users = users,
                name = "",
                age = "",
                isLoading = false,
                errorMessage = null
            )
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val users = getUsersUseCase()
            _uiState.value = _uiState.value.copy(users = users, isLoading = false)
        }
    }
}
