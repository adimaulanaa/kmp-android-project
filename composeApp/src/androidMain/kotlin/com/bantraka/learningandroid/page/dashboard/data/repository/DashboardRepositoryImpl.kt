package com.bantraka.learningandroid.page.dashboard.data.repository

import com.bantraka.learningandroid.database.User
import com.bantraka.learningandroid.page.dashboard.data.datasource.UserLocalDataSource
import com.bantraka.learningandroid.page.dashboard.domain.repository.DashboardRepository

class DashboardRepositoryImpl(
    private val localDataSource: UserLocalDataSource
) : DashboardRepository {

    override fun getUsers(): List<User> {
        return localDataSource.getUsers().map {
            User(
                id = it.id,
                name = it.name,
                age = it.age.toLong() // konversi ke Int untuk domain model
            )
        }
    }

    override fun addUser(name: String, age: Int) {
        localDataSource.insertUser(name, age)
    }
}