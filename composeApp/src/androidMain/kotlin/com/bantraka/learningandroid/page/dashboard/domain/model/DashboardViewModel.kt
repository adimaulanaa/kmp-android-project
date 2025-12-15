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
    val id: Long = 0,
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

        // 1️⃣ Validasi nama
        if (state.name.isBlank()) {
            _uiState.value = state.copy(errorMessage = "Nama harus diisi")
            return
        }

        // 2️⃣ Validasi umur
        val ageInt = state.age.toIntOrNull()
        if (ageInt == null) {
            _uiState.value = state.copy(errorMessage = "Umur harus angka")
            return
        }

        // 3️⃣ Set loading
        _uiState.value = state.copy(isLoading = true, errorMessage = null)

        // 4️⃣ Jalankan coroutine untuk menambahkan user
        viewModelScope.launch {
            try {
                addUserUseCase(state.name, ageInt)   // use case sudah suspend
                val users = getUsersUseCase()        // use case sudah suspend
                _uiState.value = state.copy(
                    users = users,
                    name = "",
                    age = "",
                    isLoading = false,
                    errorMessage = null
                )
            } catch (e: Exception) {
                _uiState.value = state.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Terjadi kesalahan"
                )
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
        val id = state.id

        if (id == 0L) return // tidak ada user untuk diedit

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
            updateUserUseCase(User(id = id, name = state.name, age = ageInt.toLong()))
            val users = getUsersUseCase()
            _uiState.value = state.copy(
                users = users,
                name = "",
                age = "",
                id = 0L,              // reset id
                isLoading = false,
                errorMessage = null
            )
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
