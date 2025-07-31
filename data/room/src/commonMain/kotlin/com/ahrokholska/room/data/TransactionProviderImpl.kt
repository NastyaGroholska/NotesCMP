package com.ahrokholska.room.data

import androidx.room.RoomDatabase
import androidx.room.Transactor
import androidx.room.useWriterConnection
import com.ahrokholska.room.di.TransactionProvider

class TransactionProviderImpl internal constructor(
    private val db: RoomDatabase
) : TransactionProvider {
    override suspend fun startTransaction(block: suspend () -> Unit) {
        db.useWriterConnection {
            it.withTransaction(Transactor.SQLiteTransactionType.IMMEDIATE) {
                block()
            }
        }
    }
}