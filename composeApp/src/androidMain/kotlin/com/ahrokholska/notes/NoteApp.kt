package com.ahrokholska.notes

import android.app.Application
import com.ahrokholska.notes.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent

class NoteApp : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@NoteApp)
            workManagerFactory()
        }
    }
}