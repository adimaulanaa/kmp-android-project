package com.bantraka.learningandroid.page.dashboard.domain.usecase

import com.bantraka.learningandroid.database.User
import com.bantraka.learningandroid.page.dashboard.domain.repository.DashboardRepository

class UpdateUserUseCase(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(user: User) {
        repository.updateUser(user)
    }
}