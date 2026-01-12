@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.android.di_utils

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope


@Module
expect class ContextModule() {

    @Single
    fun providesContextWrapper(scope: Scope): ContextWrapper
}

expect class ContextWrapper