package com.bantraka.learningandroid.page.dashboard.domain.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bantraka.learningandroid.database.User
import com.bantraka.learningandroid.page.dashboard.domain.usecase.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            _users.value = getUsersUseCase()
        }
    }

    fun addUser(name: String, age: Int) {
        viewModelScope.launch {
            addUserUseCase(name, age)
            loadUsers() // refresh data
        }
    }
}