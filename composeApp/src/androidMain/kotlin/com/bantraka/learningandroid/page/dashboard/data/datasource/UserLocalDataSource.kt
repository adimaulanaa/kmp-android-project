package com.bantraka.learningandroid.page.dashboard.data.datasource

import com.bantraka.learningandroid.database.AppDatabase

class UserLocalDataSource(
    private val database: AppDatabase
) {

    fun getUsers() =
        database.userQueries.selectAllUsers().executeAsList()

    fun insertUser(name: String, age: Int) {
        database.userQueries.insertUser(
            name = name,
            age = age.toLong()
        )
    }
}