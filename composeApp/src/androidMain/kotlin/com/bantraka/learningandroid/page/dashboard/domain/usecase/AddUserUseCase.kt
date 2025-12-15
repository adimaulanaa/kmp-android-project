package com.bantraka.learningandroid.page.dashboard.domain.usecase

import com.bantraka.learningandroid.page.dashboard.domain.repository.DashboardRepository

class AddUserUseCase(
    private val repository: DashboardRepository
) {
    operator fun invoke(name: String, age: Int) {
        repository.addUser(name, age)
    }
}