package com.bantraka.learningandroid.page.dashboard.domain.repository

import com.bantraka.learningandroid.database.User

interface DashboardRepository {
    fun getUsers(): List<User>
    fun addUser(name: String, age: Int)
}