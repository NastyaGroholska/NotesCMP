@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.ahrokholska.room.di

import com.ahrokholska.room.data.AppDatabase
import com.ahrokholska.room.data.TransactionProviderImpl
import com.android.di_utils.ContextWrapper
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.ahrokholska.room.data")
class RoomModule {
    @Single
    internal fun database(context: ContextWrapper) =
        context.getDatabaseProvider().getDatabase("app_database.db")

    @Single(binds = [TransactionProvider::class])
    internal fun transactionProvider(db: AppDatabase) = TransactionProviderImpl(db)

    @Single
    internal fun pinNoteDao(db: AppDatabase) = db.pinNoteDao()

    @Single
    internal fun finishNoteDao(db: AppDatabase) = db.finishNoteDao()

    @Single
    internal fun goalsNotesDao(db: AppDatabase) = db.goalsNotesDao()

    @Single
    internal fun guidanceNotesDao(db: AppDatabase) = db.guidanceNotesDao()

    @Single
    internal fun interestingIdeaDao(db: AppDatabase) = db.interestingIdeaDao()

    @Single
    internal fun buySomethingNotesDao(db: AppDatabase) = db.buySomethingNotesDao()

    @Single
    internal fun routineTasksNotesDao(db: AppDatabase) = db.routineTasksNotesDao()
}

internal expect fun ContextWrapper.getDatabaseProvider(): DatabaseProvider

internal interface DatabaseProvider {
    fun getDatabase(dbName: String): AppDatabase
}