package com.bantraka.learningandroid.page.dashboard.data.repository

import com.bantraka.learningandroid.database.User
import com.bantraka.learningandroid.page.dashboard.data.datasource.DashboardDataSource
import com.bantraka.learningandroid.page.dashboard.domain.repository.DashboardRepository

class DashboardRepositoryImpl(
    private val dataSource: DashboardDataSource
) : DashboardRepository {

    override suspend fun getUsers(): List<User> =
        dataSource.getUsers()

    override suspend fun addUser(name: String, age: Int) =
        dataSource.addUser(name, age)

    override suspend fun updateUser(user: User) =
        dataSource.updateUser(user)

    override suspend fun deleteUser(id: Long) =
        dataSource.deleteUser(id)
}