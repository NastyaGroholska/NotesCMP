package com.ahrokholska.notes.di

import com.ahrokholska.create_note.di.createNoteModule
import com.ahrokholska.notes_home.di.homeModule
import com.ahrokholska.room.di.roomModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(mainModule, platformModule, homeModule, roomModule, createNoteModule)
    }
}