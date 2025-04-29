package com.ahrokholska.room.di

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ahrokholska.room.data.AppDatabase
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

internal actual fun Module.getDatabase(dbName: String): KoinDefinition<AppDatabase> =
    single {
        val context: Context = get()
        val dbFile = context.getDatabasePath(dbName)
        Room.databaseBuilder<AppDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
            .setDriver(BundledSQLiteDriver())
            .build()
    }
