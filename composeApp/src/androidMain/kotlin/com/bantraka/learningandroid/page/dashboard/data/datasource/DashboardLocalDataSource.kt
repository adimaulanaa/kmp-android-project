package com.bantraka.learningandroid.page.dashboard.data.datasource

import com.bantraka.learningandroid.database.AppDatabase
import com.bantraka.learningandroid.database.User

class DashboardDataSource(private val db: AppDatabase) {

    suspend fun getUsers(): List<User> =
        db.userQueries.selectAllUsers().executeAsList()

    suspend fun addUser(name: String, age: Int) {
        db.userQueries.insertUser(name = name, age = age.toLong())
    }

    suspend fun updateUser(user: User) {
        db.userQueries.updateUser(name = user.name, age = user.age, id = user.id)
    }

    suspend fun deleteUser(id: Long) {
        db.userQueries.deleteUser(id)
    }
}