package com.bantraka.learningandroid.database

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

class DatabaseDriverFactory(
    private val context: Context
) {
    fun createDriver() =
        AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = context,
            name = "app.db"
        )
}