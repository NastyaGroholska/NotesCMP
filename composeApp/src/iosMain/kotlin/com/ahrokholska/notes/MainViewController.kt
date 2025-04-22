package com.ahrokholska.notes

import androidx.compose.ui.window.ComposeUIViewController
import com.ahrokholska.notes.di.initKoin
import com.ahrokholska.notes.presentation.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}