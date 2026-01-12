package com.ahrokholska.notes.di

import com.ahrokholska.create_note.di.createNoteModule
import com.ahrokholska.notes_home.di.homeModule
import com.ahrokholska.room.di.RoomModule
import com.android.di_utils.ContextModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            mainModule,
            platformModule,
            homeModule,
            createNoteModule,
            RoomModule().module,
            ContextModule().module,
        )
    }
}