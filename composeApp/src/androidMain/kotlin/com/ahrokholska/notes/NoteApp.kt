package com.ahrokholska.notes

import android.app.Application
import com.ahrokholska.notes.di.initKoin
import org.koin.android.ext.koin.androidContext

class NoteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@NoteApp)
        }
    }
}