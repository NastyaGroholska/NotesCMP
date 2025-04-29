package com.ahrokholska.room.di

import com.ahrokholska.api.NotesRepository
import com.ahrokholska.room.data.AppDatabase
import com.ahrokholska.room.data.NotesRepositoryImpl
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal expect fun Module.getDatabase(dbName: String): KoinDefinition<AppDatabase>

val roomModule = module {
    getDatabase("app_database.db")
    single {
        val db: AppDatabase = get()
        db.pinNoteDao()
    }
    single {
        val db: AppDatabase = get()
        db.finishNoteDao()
    }
    single {
        val db: AppDatabase = get()
        db.goalsNotesDao()
    }
    single {
        val db: AppDatabase = get()
        db.guidanceNotesDao()
    }
    single {
        val db: AppDatabase = get()
        db.interestingIdeaDao()
    }
    single {
        val db: AppDatabase = get()
        db.buySomethingNotesDao()
    }
    single {
        val db: AppDatabase = get()
        db.routineTasksNotesDao()
    }
    singleOf(::NotesRepositoryImpl) { bind<NotesRepository>() }
}