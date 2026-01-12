@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.ahrokholska.room.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ahrokholska.room.data.AppDatabase
import com.android.di_utils.ContextWrapper

internal actual fun ContextWrapper.getDatabaseProvider() = object : DatabaseProvider {
    override fun getDatabase(dbName: String): AppDatabase {
        val dbFile = context.getDatabasePath(dbName)
        return Room.databaseBuilder<AppDatabase>(
            context = context,
            name = dbFile.absolutePath
        ).setDriver(BundledSQLiteDriver())
            .build()
    }
}