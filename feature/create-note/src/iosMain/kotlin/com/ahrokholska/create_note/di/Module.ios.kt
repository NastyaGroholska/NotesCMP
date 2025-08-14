package com.ahrokholska.create_note.di

import com.ahrokholska.create_note.domain.useCase.Copier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    factory {
        Copier(
            repository = get(),
            ioDispatcher = Dispatchers.IO
        )
    }
}