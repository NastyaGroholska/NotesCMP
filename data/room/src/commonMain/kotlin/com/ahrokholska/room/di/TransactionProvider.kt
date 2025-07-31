package com.ahrokholska.room.di

interface TransactionProvider {
    suspend fun startTransaction(block: suspend () -> Unit)
}