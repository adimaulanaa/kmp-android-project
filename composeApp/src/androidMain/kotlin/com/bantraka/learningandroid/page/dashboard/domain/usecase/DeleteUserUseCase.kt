package com.bantraka.learningandroid.page.dashboard.domain.usecase

import com.bantraka.learningandroid.database.User
import com.bantraka.learningandroid.page.dashboard.domain.repository.DashboardRepository

class DeleteUserUseCase(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteUser(id)
    }
}