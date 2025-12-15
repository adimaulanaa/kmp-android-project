package com.bantraka.learningandroid.page.dashboard.domain.repository

import com.bantraka.learningandroid.database.User

interface DashboardRepository {
    suspend fun getUsers(): List<User>
    suspend fun addUser(name: String, age: Int)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(id: Long)
}