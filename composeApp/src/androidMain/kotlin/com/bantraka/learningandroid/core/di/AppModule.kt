package com.bantraka.learningandroid.core.di

import com.bantraka.learningandroid.database.AppDatabase
import com.bantraka.learningandroid.page.dashboard.data.datasource.UserLocalDataSource
import com.bantraka.learningandroid.page.dashboard.data.repository.DashboardRepositoryImpl
import com.bantraka.learningandroid.page.dashboard.domain.model.DashboardViewModel
import com.bantraka.learningandroid.page.dashboard.domain.repository.DashboardRepository
import com.bantraka.learningandroid.page.dashboard.domain.usecase.AddUserUseCase
import com.bantraka.learningandroid.page.dashboard.domain.usecase.GetUsersUseCase
import android.content.Context
import com.bantraka.learningandroid.database.DatabaseModule

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
    val userLocalDataSource by lazy {
        UserLocalDataSource(database)
    }

    // Repository
    val dashboardRepository: DashboardRepository by lazy {
        DashboardRepositoryImpl(userLocalDataSource)
    }

    // UseCases
    val getUsersUseCase by lazy {
        GetUsersUseCase(dashboardRepository)
    }

    val addUserUseCase by lazy {
        AddUserUseCase(dashboardRepository)
    }

    // ViewModel provider
    fun provideDashboardViewModel(): DashboardViewModel {
        return DashboardViewModel(getUsersUseCase, addUserUseCase)
    }
}