package com.ahrokholska.create_note.di

import androidx.work.WorkManager
import com.ahrokholska.create_note.data.CopyImageWorker
import com.ahrokholska.create_note.domain.useCase.Copier
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.workmanager.dsl.worker
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    worker {
        CopyImageWorker(
            repository = get(),
            ioDispatcher = Dispatchers.IO,
            appContext = get(),
            workerParams = get()
        )
    }
    single { WorkManager.getInstance(get()) }
    factoryOf(::Copier)
}