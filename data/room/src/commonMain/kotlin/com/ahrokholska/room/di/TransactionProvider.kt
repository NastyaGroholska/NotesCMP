package com.ahrokholska.room.di

internal interface TransactionProvider {
    suspend fun startTransaction(block: suspend () -> Unit)
}