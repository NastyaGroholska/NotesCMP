package com.ahrokholska.copy_image.di

import com.ahrokholska.copy_image.domain.useCase.CopyAndUpdateImageUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal expect val platformModule: Module

val copyImageModule = module {
    includes(platformModule)
    factoryOf(::CopyAndUpdateImageUseCase)
}