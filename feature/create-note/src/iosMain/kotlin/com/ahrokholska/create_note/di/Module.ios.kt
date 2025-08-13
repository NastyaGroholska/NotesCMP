package com.ahrokholska.create_note.di

import com.ahrokholska.create_note.domain.useCase.Copier
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    factoryOf(::Copier)
}