package com.bantraka.learningandroid.page.dashboard.domain.usecase

import com.bantraka.learningandroid.page.dashboard.domain.repository.DashboardRepository

class AddUserUseCase(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(name: String, ageStr: String) : Result<Unit> {
        // Validasi
        if (name.isBlank()) return Result.failure(Exception("Nama harus diisi"))

        val age = ageStr.toIntOrNull() ?: return Result.failure(Exception("Umur harus angka"))

        if (age < 17) return Result.failure(Exception("Umur minimal 17 tahun"))

        repository.addUser(name, age)
        return Result.success(Unit)
    }
}