package com.bantraka.learningandroid.core.di

import com.bantraka.learningandroid.page.dashboard.data.repository.DashboardRepositoryImpl
import com.bantraka.learningandroid.page.dashboard.domain.model.DashboardViewModel
import com.bantraka.learningandroid.page.dashboard.domain.repository.DashboardRepository
import com.bantraka.learningandroid.page.dashboard.domain.usecase.AddUserUseCase
import com.bantraka.learningandroid.page.dashboard.domain.usecase.GetUsersUseCase
import android.content.Context
import com.bantraka.learningandroid.database.DatabaseModule
import com.bantraka.learningandroid.page.dashboard.data.datasource.DashboardDataSource
import com.bantraka.learningandroid.page.dashboard.domain.usecase.DeleteUserUseCase
import com.bantraka.learningandroid.page.dashboard.domain.usecase.UpdateUserUseCase
import kotlin.getValue

object AppModule {

    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context
    }

    // Database singleton
    val database by lazy {
        DatabaseModule.createDatabase(appContext)
    }

    // DataSource
    val dashboardLocalDataSource by lazy {
        DashboardDataSource(database)
    }

    // Repository
    val dashboardRepository: DashboardRepository by lazy {
        DashboardRepositoryImpl(dashboardLocalDataSource)
    }

    // UseCases
    val getUsersUseCase by lazy {
        GetUsersUseCase(dashboardRepository)
    }

    val addUserUseCase by lazy {
        AddUserUseCase(dashboardRepository)
    }

    val updateUserUseCase by lazy {
        UpdateUserUseCase(dashboardRepository)
    }

    val deleteUserUseCase by lazy {
        DeleteUserUseCase(dashboardRepository)
    }

    // ViewModel provider
    fun provideDashboardViewModel(): DashboardViewModel {
        return DashboardViewModel(
            getUsersUseCase,
            addUserUseCase,
            updateUserUseCase,
            deleteUserUseCase,
            )
    }
}