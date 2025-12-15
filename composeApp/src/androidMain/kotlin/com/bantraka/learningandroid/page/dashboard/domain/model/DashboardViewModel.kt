package com.bantraka.learningandroid.page.dashboard.domain.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bantraka.learningandroid.database.User
import com.bantraka.learningandroid.page.dashboard.domain.usecase.AddUserUseCase
import com.bantraka.learningandroid.page.dashboard.domain.usecase.DeleteUserUseCase
import com.bantraka.learningandroid.page.dashboard.domain.usecase.GetUsersUseCase
import com.bantraka.learningandroid.page.dashboard.domain.usecase.UpdateUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DashboardUiState(
    val users: List<User> = emptyList(),
    val id: Long? = null,
    val name: String = "",
    val age: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class DashboardViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
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
        _uiState.value = state.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            val result = addUserUseCase(state.name, state.age)
            result.onSuccess {
                val users = getUsersUseCase()
                _uiState.value = state.copy(
                    users = users,
                    name = "",
                    age = "",
                    isLoading = false
                )
            }.onFailure { e ->
                _uiState.value = state.copy(isLoading = false, errorMessage = e.message)
            }
        }
    }

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val users = getUsersUseCase()
            _uiState.value = _uiState.value.copy(users = users, isLoading = false)
        }
    }

    fun onEditUser(user: User) {
        // isi field input dengan data user yang akan diedit
        _uiState.value = _uiState.value.copy(
            name = user.name,
            age = user.age.toString(),
            id = user.id // tambahkan field Long? di UiState
        )
    }

    fun editUser() {
        val state = _uiState.value
        if (state.id == null) return
        _uiState.value = state.copy(isLoading = true, errorMessage = null)
        val userToUpdate = User(id = state.id, name = state.name, age = state.age.toLong())
        viewModelScope.launch {
            val result = updateUserUseCase(userToUpdate)
            result.onSuccess {
                val users = getUsersUseCase()
                _uiState.value = state.copy(
                    users = users,
                    name = "",
                    age = "",
                    id = null,              // reset id
                    isLoading = false,
                    errorMessage = null
                )
            }.onFailure { e ->
                _uiState.value = state.copy(isLoading = false, errorMessage = e.message)
            }
        }
    }


    fun deleteUser(id: Long) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            deleteUserUseCase(id)
            val users = getUsersUseCase()
            _uiState.value = _uiState.value.copy(users = users, isLoading = false)
        }
    }
}
