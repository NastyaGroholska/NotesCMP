package com.ahrokholska.room.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ahrokholska.room.data.AppDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

internal actual fun Module.getDatabase(dbName: String): KoinDefinition<AppDatabase> =
    single {
        Room.databaseBuilder<AppDatabase>(
            name = documentDirectory() + "/" + dbName
        )
            .setDriver(BundledSQLiteDriver())
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