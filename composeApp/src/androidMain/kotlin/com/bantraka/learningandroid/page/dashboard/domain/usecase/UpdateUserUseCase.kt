package com.bantraka.learningandroid.page.dashboard.domain.usecase

import com.bantraka.learningandroid.database.User
import com.bantraka.learningandroid.page.dashboard.domain.repository.DashboardRepository

class UpdateUserUseCase(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(user: User) : Result<Unit> {
        // Validasi
        if (user.id == null) return Result.failure(Exception("Nama harus diisi"))

        if (user.name.isBlank()) return Result.failure(Exception("Nama harus diisi"))

        if (user.age < 17) return Result.failure(Exception("Umur minimal 17 tahun"))

        repository.updateUser(user)
        return Result.success(Unit)
    }
}