@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.ahrokholska.room.di

import androidx.room.Room
import com.ahrokholska.room.data.AppDatabase
import com.android.di_utils.ContextWrapper
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

internal actual fun ContextWrapper.getDatabaseProvider() = object : DatabaseProvider {
    override fun getDatabase(dbName: String): AppDatabase =
        Room.databaseBuilder<AppDatabase>(
            name = documentDirectory() + "/" + dbName
        ).setDriver(androidx.sqlite.driver.bundled.BundledSQLiteDriver())
            .build()
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}