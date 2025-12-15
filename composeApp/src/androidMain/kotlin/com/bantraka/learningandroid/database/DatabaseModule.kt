package com.bantraka.learningandroid.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver

object DatabaseModule {

    fun createDatabase(context: Context): AppDatabase {
        val driver: SqlDriver =
            DatabaseDriverFactory(context).createDriver()

        return AppDatabase(driver)
    }
}