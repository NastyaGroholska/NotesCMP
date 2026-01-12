@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.android.di_utils

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

actual class ContextWrapper

@Module
actual class ContextModule {
    @Single
    actual fun providesContextWrapper(scope: Scope): ContextWrapper = ContextWrapper()
}