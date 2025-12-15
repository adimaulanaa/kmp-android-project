package com.bantraka.learningandroid.page.dashboard.domain.usecase

import com.bantraka.learningandroid.database.User
import com.bantraka.learningandroid.page.dashboard.domain.repository.DashboardRepository

class GetUsersUseCase(
    private val repository: DashboardRepository
) {
    operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}